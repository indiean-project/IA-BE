package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.pageDto.ListDto;
import com.ia.indieAn.common.pageDto.PageInfo;
import com.ia.indieAn.domain.board.dto.BoardDto;
import com.ia.indieAn.domain.board.repository.BoardRepository;
import com.ia.indieAn.domain.board.repository.ContentLikeLogRepository;
import com.ia.indieAn.domain.imgurl.repository.ImgUrlRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.ContentLikeLog;
import com.ia.indieAn.entity.board.ImgUrl;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.enumType.BrTypeEnum;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import com.ia.indieAn.type.enumType.FabcTypeEnum;
import com.ia.indieAn.type.enumType.KcTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ContentLikeLogRepository contentLikeLogRepository;

    @Autowired
    ImgUrlRepository imgUrlRepository;

    public ListDto boardList(Pageable pageable, String deleteYn, ContentTypeEnum contentTypeEnum) {

        Page<Board> pages = boardRepository.findAllByDeleteYnAndContentTypeNo(pageable, deleteYn, contentTypeEnum);
        List<Board> boardList = pages.getContent();
        int totalPage = pages.getTotalPages(); //전체 페이지 개수
        int currentPage = pages.getNumber() + 1;     //현재 페이지 번호
        int totalCount = (int) pages.getTotalElements(); //전체 테이블 건수
        int boardLimit = 10;
        PageInfo pageInfo = new PageInfo(totalPage, currentPage, totalCount, boardLimit);

        ArrayList<BoardDto> boardListDto = new ArrayList<>();

        for(int i = 0; i < boardList.size(); i++) {
            boardListDto.add(new BoardDto(boardList.get(i)));
        }

        for(int i = 0; i < boardListDto.size(); i++) {
            int result = contentLikeLogRepository.countByContentNoAndBrTypeAndLikeYn(boardListDto.get(i).getBoardNo(), BrTypeEnum.BOARD, "Y");
            boardListDto.get(i).setLikeCount(result);
        }

        if(contentTypeEnum == ContentTypeEnum.PROUD) {
            for(int i = 0; i < boardListDto.size(); i++) {
                ArrayList<ImgUrl> result = imgUrlRepository.findByContentNoAndFabcTypeAndKcType(boardListDto.get(i).getBoardNo(), FabcTypeEnum.BOARD, KcTypeEnum.CONTENT);
                if(result.size() > 0) {
                    boardListDto.get(i).setImgUrl(result.get(0).getImgUrl());
                }
            }
        }
        log.info("result : {}", boardListDto);


        ListDto listDto  = ListDto.builder()
                .listDto(boardListDto)
                .pageinfo(pageInfo)
                .build();

        return listDto;
    }



    @Transactional(rollbackFor = CustomException.class)
    public Board boardEnroll(Board board) {
        Member member = userRepository.findByUserNo(board.getMember().getUserNo());
        board.setMember(member);
        return boardRepository.save(board);
    }

    @Transactional(rollbackFor = CustomException.class)
    public void viewCount(int boardNo) {
        Board board = boardRepository.findByBoardNo(boardNo);
        board.setViewCount(board.getViewCount()+1);

        boardRepository.save(board);
    }

    @Transactional(rollbackFor = CustomException.class)
    public void likeCount(ContentLikeLog contentLikeLog) {
        ContentLikeLog clike = contentLikeLogRepository.findByMember_UserNoAndContentNoAndBrType(contentLikeLog.getMember().getUserNo(), contentLikeLog.getContentNo(), contentLikeLog.getBrType());

        if(clike == null) {
            contentLikeLog.setLikeYn("Y");
            contentLikeLogRepository.save(contentLikeLog);
        } else if (clike.getLikeYn().equals("N")) {
            clike.setLikeYn("Y");
            contentLikeLogRepository.save(clike);
        } else {
            clike.setLikeYn("N");
            contentLikeLogRepository.save(clike);
        }

    }

    public ArrayList boardAmount() {
        ArrayList amountList = new ArrayList();

        int freeAmount = boardRepository.countByContentTypeNoAndDeleteYn(ContentTypeEnum.FREE, "N");
        int proudAmount = boardRepository.countByContentTypeNoAndDeleteYn(ContentTypeEnum.PROUD, "N");
        int coloAmount = boardRepository.countByContentTypeNoAndDeleteYn(ContentTypeEnum.COLO, "N");

        amountList.add(freeAmount);
        amountList.add(proudAmount);
        amountList.add(coloAmount);

        return amountList;
    }

}
