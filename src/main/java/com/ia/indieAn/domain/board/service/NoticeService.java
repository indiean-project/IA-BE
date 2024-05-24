package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.common.pageDto.ListDto;
import com.ia.indieAn.common.pageDto.PageInfo;
import com.ia.indieAn.domain.board.dto.NoticeDto;
import com.ia.indieAn.domain.board.dto.NoticeProjection;
import com.ia.indieAn.domain.board.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class NoticeService {

    @Autowired
    NoticeRepository noticeRepository;

    public ListDto noticeList(Pageable pageable, String title) {
        Page<NoticeProjection> pages = noticeRepository.findAll(pageable, title);
        Page<NoticeDto> pagesDto = pages.map(NoticeDto::convertNoticeDto);

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
}
