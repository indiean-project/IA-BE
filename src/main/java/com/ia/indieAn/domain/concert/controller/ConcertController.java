package com.ia.indieAn.domain.concert.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.concert.dto.ConcertDto;
import com.ia.indieAn.domain.concert.service.ConcertService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/concert")
@CrossOrigin
public class ConcertController {

    @Autowired
    ConcertService concertService;

    @RequestMapping("/concertList")
    public ResponseEntity<ResponseTemplate> concertList(@PageableDefault ( size=8, sort="concertNo", direction = Sort.Direction.DESC) Pageable pageable){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();


        ArrayList<ConcertDto> result = concertService.concertList(pageable);
        log.info("result = {}",result);
        response.setData(result);
        response.setStatus(StatusEnum.SUCCESS);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
