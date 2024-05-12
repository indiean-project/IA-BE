package com.ia.indieAn.domain.concert.service;

import com.ia.indieAn.domain.concert.dto.ConcertDto;
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

    public ArrayList<ConcertDto> concertList(Pageable pageable){

        Page<Concert> page = concertRepository.findAllByDeleteYn(pageable,"n");
       int totalPage = page.getTotalPages(); //전체 페이지 개수
       int currentPage = page.getNumber();     //현재 페이지 번호
       long totalCount = page.getTotalElements(); //전체 테이블 건수
       log.info("totalPage : {} currentPage : {} totalCount : {}",totalPage,currentPage,totalCount);
        List<Concert> list = page.getContent();


        ArrayList<ConcertDto> listDto = new ArrayList<>();
        for(int i =0 ; i < list.size();i++){
                listDto.add(new ConcertDto(list.get(i)));
        }

        return listDto;
    }

}
