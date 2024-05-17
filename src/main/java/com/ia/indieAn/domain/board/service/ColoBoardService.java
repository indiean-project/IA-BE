package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.pageDto.ListDto;
import com.ia.indieAn.common.pageDto.PageInfo;
import com.ia.indieAn.domain.board.dto.ColoBoardDto;
import com.ia.indieAn.domain.board.repository.BoardRepository;
import com.ia.indieAn.domain.board.repository.ColoBoardRepository;
import com.ia.indieAn.domain.board.repository.ColoLogRepository;
import com.ia.indieAn.domain.board.repository.ContentLikeLogRepository;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.BoardColo;
import com.ia.indieAn.type.enumType.BrTypeEnum;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import com.ia.indieAn.type.enumType.RlTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ColoBoardService {

    @Autowired
    ColoBoardRepository coloBoardRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ContentLikeLogRepository contentLikeLogRepository;

    @Autowired
    ColoLogRepository coloLogRepository;

    @Transactional(rollbackFor = CustomException.class)
    public void coloBoardEnroll(BoardColo boardColo) {
        coloBoardRepository.save(boardColo);
    }

    public ListDto coloBoardList(Pageable pageable, String deleteYn) {
        Page<Board> pages = boardRepository.findAllByDeleteYnAndContentTypeNo(pageable, deleteYn, ContentTypeEnum.COLO);
        List<Board> list = pages.getContent();
        int totalPage = pages.getTotalPages(); //전체 페이지 개수
        int currentPage = pages.getNumber() + 1;     //현재 페이지 번호
        int totalCount = (int) pages.getTotalElements(); //전체 테이블 건수
        int boardLimit = 5;
        PageInfo pageInfo = new PageInfo(totalPage, currentPage, totalCount, boardLimit);

        ArrayList<ColoBoardDto> coloBoardListDto = new ArrayList<>();

        for(int i = 0; i < list.size(); i++) {
            if (list.get(i).getBoardColo() != null) {
                coloBoardListDto.add(new ColoBoardDto(list.get(i)));
            }
        }

        for(int i = 0; i < coloBoardListDto.size(); i++) {
            int result = contentLikeLogRepository.countByContentNoAndBrTypeAndLikeYn(coloBoardListDto.get(i).getBoardNo(), BrTypeEnum.BOARD, "Y");
            coloBoardListDto.get(i).setLikeCount(result);
        }

        for(int i = 0; i < coloBoardListDto.size(); i++) {
            int left = coloLogRepository.countByBoardColo_ColoNoAndVoteAndCancelYn(coloBoardListDto.get(i).getColoNo(), RlTypeEnum.LEFT, "N");
            int right = coloLogRepository.countByBoardColo_ColoNoAndVoteAndCancelYn(coloBoardListDto.get(i).getColoNo(), RlTypeEnum.RIGHT, "N");
            coloBoardListDto.get(i).setColLeftCount(left);
            coloBoardListDto.get(i).setColRightCount(right);
        }

        ListDto listDto  = ListDto.builder()
                .listDto(coloBoardListDto)
                .pageinfo(pageInfo)
                .build();

        return listDto;
    }
}
