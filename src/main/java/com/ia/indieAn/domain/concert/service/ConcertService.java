package com.ia.indieAn.domain.concert.service;

import com.ia.indieAn.common.pageDto.BoardInfoDto;
import com.ia.indieAn.common.pageDto.PageInfo;
import com.ia.indieAn.domain.concert.dto.*;
import com.ia.indieAn.common.pageDto.ListDto;
import com.ia.indieAn.domain.concert.repository.ConcertLineupRepository;
import com.ia.indieAn.domain.concert.repository.ConcertRepository;
import com.ia.indieAn.domain.imgurl.repository.ImgUrlRepository;
import com.ia.indieAn.entity.board.ImgUrl;
import com.ia.indieAn.entity.concert.Concert;
import com.ia.indieAn.entity.concert.ConcertLineup;
import com.ia.indieAn.type.enumType.FabcTypeEnum;
import com.ia.indieAn.type.enumType.KcTypeEnum;
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
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConcertService {


    private final ConcertRepository concertRepository;
    private final ConcertLineupRepository concertLineupRepository;
    private final ImgUrlRepository imgUrlRepository;
    public ListDto concertList(BoardInfoDto bInfo) {
        if (bInfo.getSort().equals("createDate")) {
            Pageable pageable = PageRequest.of(bInfo.getPage() - 1, 8);
            Page<ConcertProjection> page = concertRepository.findConcertCreateList(pageable,bInfo.getKeyword());
            Page<ConcertDto> concertDtoPage = page.map(ConcertDto::convertToPage);
            int totalPage = page.getTotalPages(); //전체 페이지 개수
            int currentPage = page.getNumber() + 1;     //현재 페이지 번호
            int totalCount = (int) page.getTotalElements(); //전체 테이블 건수
            int boardLimit = 5;
            PageInfo pageInfo = new PageInfo(totalPage, currentPage, totalCount, boardLimit);
            ArrayList<ConcertDto> listDto = new ArrayList<>();
            ListDto concertListDto = ListDto.builder()
                    .listDto(concertDtoPage.getContent())
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

    public List<ConcertDto> calendarList(Date firstDate, Date lastDate) {

       List<Concert> calendar = concertRepository.findByStartDateBetween(firstDate,lastDate);

       return calendar.stream().map(ConcertDto::new).toList();
    }

    public ConcertDetailDto concertDetail(int concertNo) {

        Concert concert = concertRepository.findByConcertNo(concertNo);
        ArrayList<ImgUrl> imgUrl = imgUrlRepository.findByContentNoAndFabcTypeAndKcType(concertNo, FabcTypeEnum.CONCERT, KcTypeEnum.KING);
        List<LineupPorjection> lineups = concertLineupRepository.findByLinupList(concertNo);
        List<ConcertLineupDto> concertLineupDtos = lineups.stream().map(ConcertLineupDto::convertToLineupDto).toList();
        log.info("concertLineupDtos={}",concertLineupDtos);
        ConcertDetailDto concertDetailDto = ConcertDetailDto.builder()
                .concertNo(concert.getConcertNo())
                .concertTitle(concert.getConcertTitle())
                .location(concert.getLocation())
                .startDate(concert.getStartDate())
                .endDate(concert.getEndDate())
                .concertInfo(concert.getConcertInfo())
                .concertLineupList(concertLineupDtos)
                .ticketUrl(concert.getTicketUrl())
                .concertPrice(concert.getConcertPrice())
                .runtime(concert.getRuntime())
                .build();
        if(imgUrl.size() >0){
            concertDetailDto.setTitleUrl(imgUrl.get(0).getImgUrl());
        }

        return concertDetailDto;
    }
}
