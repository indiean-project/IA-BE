package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.pageDto.ListDto;
import com.ia.indieAn.common.pageDto.PageInfo;
import com.ia.indieAn.domain.board.dto.BoardProjection;
import com.ia.indieAn.domain.board.dto.ColoBoardDto;
import com.ia.indieAn.domain.board.repository.BoardRepository;
import com.ia.indieAn.domain.board.repository.ColoBoardRepository;
import com.ia.indieAn.entity.board.BoardColo;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ColoBoardService {

    @Autowired
    ColoBoardRepository coloBoardRepository;

    @Autowired
    BoardRepository boardRepository;

    @Transactional(rollbackFor = CustomException.class)
    public void coloBoardEnroll(BoardColo boardColo) {

        coloBoardRepository.save(boardColo);
    }

    public ListDto coloBoardList(Pageable pageable, String deleteYn) {
        Page<BoardProjection> pages = boardRepository.findAll(pageable, Integer.parseInt(ContentTypeEnum.COLO.getCode()), "");
        Page<ColoBoardDto> pagesDto = pages.map(ColoBoardDto::convertColoBoardDto);

        int totalPage = pagesDto.getTotalPages(); //전체 페이지 개수
        int currentPage = pagesDto.getNumber() + 1;     //현재 페이지 번호
        int totalCount = (int) pagesDto.getTotalElements(); //전체 테이블 건수
        int boardLimit = 5;
        PageInfo pageInfo = new PageInfo(totalPage, currentPage, totalCount, boardLimit);

        ListDto listDto  = ListDto.builder()
                .listDto(pagesDto.getContent())
                .pageinfo(pageInfo)
                .build();

        return listDto;
    }
}
