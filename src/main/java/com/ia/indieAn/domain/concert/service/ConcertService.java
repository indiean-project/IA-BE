package com.ia.indieAn.domain.concert.service;

import com.ia.indieAn.common.pageDto.PageInfo;
import com.ia.indieAn.domain.concert.dto.ConcertDto;
import com.ia.indieAn.domain.concert.dto.ConcertListDto;
import com.ia.indieAn.domain.concert.repository.ConcertRepository;
import com.ia.indieAn.entity.concert.Concert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ConcertService {

    @Autowired
    ConcertRepository concertRepository;

    public ConcertListDto concertList(Pageable pageable){

        Page<Concert> page = concertRepository.findAllByDeleteYn(pageable,"N");
       int totalPage = page.getTotalPages(); //전체 페이지 개수
       int currentPage = page.getNumber()+1;     //현재 페이지 번호
       int totalCount = (int)page.getTotalElements(); //전체 테이블 건수
       int boardLimit = 5;
        PageInfo pageInfo = new PageInfo(totalPage,currentPage,totalCount,boardLimit);
       List<Concert> list = page.getContent();
        log.info("pageinfo = {}",pageInfo);

        ArrayList<ConcertDto> listDto = new ArrayList<>();
        for(int i =0 ; i < list.size();i++){
                listDto.add(new ConcertDto(list.get(i)));
        }
        ConcertListDto concertListDto = ConcertListDto.builder()
                                        .listDto(listDto)
                                        .pageinfo(pageInfo)
                                        .build();
        return concertListDto;
    }

}
