package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
import com.ia.indieAn.common.pageDto.ListDto;
import com.ia.indieAn.common.pageDto.PageInfo;
import com.ia.indieAn.domain.board.dto.BoardDto;
import com.ia.indieAn.domain.board.dto.BoardProjection;
import com.ia.indieAn.domain.board.repository.BoardRepository;
import com.ia.indieAn.domain.board.repository.ContentLikeLogRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.ContentLikeLog;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

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

    public ListDto boardList(Pageable pageable, ContentTypeEnum contentTypeEnum, String title) {
        Page<BoardProjection> pages = boardRepository.findAll(pageable, Integer.parseInt(contentTypeEnum.getCode()), title);
        Page<BoardDto> pagesDto = pages.map(BoardDto::convertBoardDto);

        int totalPage = pagesDto.getTotalPages(); //전체 페이지 개수
        int currentPage = pagesDto.getNumber() + 1;     //현재 페이지 번호
        int totalCount = (int) pagesDto.getTotalElements(); //전체 테이블 건수
        int boardLimit = 10;
        PageInfo pageInfo = new PageInfo(totalPage, currentPage, totalCount, boardLimit);

        ListDto listDto  = ListDto.builder()
                .listDto(pagesDto.getContent())
                .pageinfo(pageInfo)
                .build();

        return listDto;
    }

    @Transactional(rollbackFor = CustomException.class)
    public Board boardEnroll(Board board) {
        Member member = userRepository.findByUserNo(board.getMember().getUserNo());
        Board b = boardRepository.findByBoardNo(board.getBoardNo());

        if (b == null) {
            board.setMember(member);
            return boardRepository.save(board);
        }
        b.setUpdateDate(Date.valueOf(LocalDate.now()));
        b.setBoardTitle(board.getBoardTitle());
        b.setBoardContent(board.getBoardContent());


        return boardRepository.save(b);
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

    @Transactional(rollbackFor = CustomException.class)
    public void boardDelete(Board board) {
        Board b = boardRepository.findByBoardNo(board.getBoardNo());
        b.setDeleteYn("Y");
        boardRepository.save(b);
    }

    public BoardDto boardDetail(int boardNo) {
        BoardProjection bp = boardRepository.findDetail(boardNo)
                .orElseThrow(()->new CustomException(ErrorCode.BOARD_NOT_FOUND));

        log.info("bp : {}", bp);
//        if(bp == null) {
//            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
//        }

        BoardDto b = BoardDto.builder()
                .boardNo(bp.getBoardNo())
                .boardContent(bp.getBoardContent())
                .boardTitle(bp.getBoardTitle())
                .userNo(bp.getUserNo())
                .nickname(bp.getNickname())
                .enrollDate(bp.getEnrollDate())
                .updateDate(bp.getUpdateDate())
                .viewCount(bp.getViewCount())
                .userRole(bp.getUserRole())
                .likeCount(bp.getLikeCount())
                .replies(bp.getReplies())
                .build();

        return b;
    }

}
