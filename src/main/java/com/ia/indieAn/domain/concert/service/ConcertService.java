package com.ia.indieAn.domain.concert.service;

import com.ia.indieAn.common.pageDto.BoardInfoDto;
import com.ia.indieAn.common.pageDto.PageInfo;
import com.ia.indieAn.domain.concert.dto.ConcertDetailDto;
import com.ia.indieAn.domain.concert.dto.ConcertDto;
import com.ia.indieAn.common.pageDto.ListDto;
import com.ia.indieAn.domain.concert.dto.ConcertProjection;
import com.ia.indieAn.domain.concert.repository.ConcertLineupRepository;
import com.ia.indieAn.domain.concert.repository.ConcertRepository;
import com.ia.indieAn.entity.concert.Concert;
import com.ia.indieAn.entity.concert.ConcertLineup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConcertService {


    private final ConcertRepository concertRepository;
    private final ConcertLineupRepository concertLineupRepository;

    public ListDto concertList(BoardInfoDto bInfo) {
        if (bInfo.getSort().equals("createDate")) {
            Pageable pageable = PageRequest.of(bInfo.getPage() - 1, 8, Sort.by(Sort.Direction.DESC, "createDate"));
            Page<Concert> page = concertRepository.findByDeleteYnAndConcertTitleContaining(pageable, "N", bInfo.getKeyword());
            int totalPage = page.getTotalPages(); //전체 페이지 개수
            int currentPage = page.getNumber() + 1;     //현재 페이지 번호
            int totalCount = (int) page.getTotalElements(); //전체 테이블 건수
            int boardLimit = 5;
            PageInfo pageInfo = new PageInfo(totalPage, currentPage, totalCount, boardLimit);
            List<Concert> list = page.getContent();
            ArrayList<ConcertDto> listDto = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                listDto.add(new ConcertDto(list.get(i)));
            }

            ListDto concertListDto = ListDto.builder()
                    .listDto(listDto)
                    .pageinfo(pageInfo)
                    .build();
            return concertListDto;
        } else {
            Pageable pageable = PageRequest.of(bInfo.getPage() - 1, 8);
            Page<ConcertProjection> page = concertRepository.findAllBySysDate(pageable, bInfo.getKeyword());
            Page<ConcertDto> concertDtoPage = page.map(ConcertDto::convertToPage);
            int totalPage = page.getTotalPages(); //전체 페이지 개수
            int currentPage = page.getNumber() + 1;     //현재 페이지 번호
            int totalCount = (int) page.getTotalElements(); //전체 테이블 건수
            int boardLimit = 5;                             //
            PageInfo pageInfo = new PageInfo(totalPage, currentPage, totalCount, boardLimit);
            ListDto listDto = ListDto.builder()
                    .listDto(concertDtoPage.getContent())
                    .pageinfo(pageInfo)
                    .build();
            return listDto;
        }
    }

    public List<Concert> calendarList(Date firstDate, Date lastDate) {

       List<Concert> calendar = concertRepository.findByStartDateBetween(firstDate,lastDate);

       return  calendar;
    }

    public ConcertDetailDto concertDetail(int concertNo) {

        Concert concert = concertRepository.findByConcertNo(concertNo);

        List<ConcertLineup> lineups = concertLineupRepository.findByConcert_ConcertNo(concertNo);

        ConcertDetailDto concertDetailDto = ConcertDetailDto.builder()
                .concertNo(concert.getConcertNo())
                .concertTitle(concert.getConcertTitle())
                .location(concert.getLocation())
                .startDate(concert.getStartDate())
                .endDate(concert.getEndDate())
                .concertInfo(concert.getConcertInfo())
                .concertLineupList(lineups)
                .ticketUrl(concert.getTicketUrl())
                .concertPrice(concert.getConcertPrice())
                .runtime(concert.getRuntime())
                .build();

        return concertDetailDto;
    }
}
