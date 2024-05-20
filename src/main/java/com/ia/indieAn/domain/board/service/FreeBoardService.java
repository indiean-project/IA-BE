package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.common.pageDto.ListDto;
import com.ia.indieAn.common.pageDto.PageInfo;
import com.ia.indieAn.domain.board.dto.BoardDto;
import com.ia.indieAn.domain.board.repository.BoardRepository;
import com.ia.indieAn.domain.board.repository.ContentLikeLogRepository;
import com.ia.indieAn.domain.board.repository.FreeBoardRepository;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.type.enumType.BrTypeEnum;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FreeBoardService {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @Autowired
    ContentLikeLogRepository contentLikeLogRepository;

    @Autowired
    BoardRepository boardRepository;

    public ListDto freeBoardList(Pageable pageable, String deleteYn) {

        Page<Board> pages = boardRepository.findAllByDeleteYnAndContentTypeNo(pageable, deleteYn, ContentTypeEnum.FREE);
        List<Board> boardList = pages.getContent();
        int totalPage = pages.getTotalPages(); //전체 페이지 개수
        int currentPage = pages.getNumber() + 1;     //현재 페이지 번호
        int totalCount = (int) pages.getTotalElements(); //전체 테이블 건수
        int boardLimit = 10;
        PageInfo pageInfo = new PageInfo(totalPage, currentPage, totalCount, boardLimit);

        ArrayList<BoardDto> freeBoardListDto = new ArrayList<>();

        for(int i = 0; i < boardList.size(); i++) {
            freeBoardListDto.add(new BoardDto(boardList.get(i)));
        }

        for(int i = 0; i < freeBoardListDto.size(); i++) {
            int result = contentLikeLogRepository.countByContentNoAndBrTypeAndLikeYn(freeBoardListDto.get(i).getBoardNo(), BrTypeEnum.BOARD, "Y");
            freeBoardListDto.get(i).setLikeCount(result);
        }

        ListDto listDto  = ListDto.builder()
                .listDto(freeBoardListDto)
                .pageinfo(pageInfo)
                .build();

        return listDto;
    }
}
