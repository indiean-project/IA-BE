package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.common.pageDto.ListDto;
import com.ia.indieAn.common.pageDto.PageInfo;
import com.ia.indieAn.domain.board.dto.NoticeDto;
import com.ia.indieAn.domain.board.dto.NoticeProjection;
import com.ia.indieAn.domain.board.repository.NoticeRepository;
import com.ia.indieAn.entity.board.Notice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public NoticeDto noticeDetail(int noticeNo) {
        Notice n = noticeRepository.findByNoticeNoAndDeleteYn(noticeNo, "N");
        NoticeDto nDto = NoticeDto.builder()
                .noticeNo(n.getNoticeNo())
                .noticeTitle(n.getNoticeTitle())
                .noticeContent(n.getNoticeContent())
                .viewCount(n.getViewCount())
                .updateDate(String.valueOf(n.getUpdateDate()))
                .enrollDate(String.valueOf(n.getEnrollDate()))
                .nickName(n.getMember().getNickname())
                .build();

        return nDto;
    }
}
