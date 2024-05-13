package com.ia.indieAn.domain.concert.controller;

import com.ia.indieAn.common.pageDto.BoardInfoDto;
import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.concert.dto.ConcertDto;
import com.ia.indieAn.domain.concert.dto.ConcertListDto;
import com.ia.indieAn.domain.concert.service.ConcertService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/concert")
@CrossOrigin
@RequiredArgsConstructor
public class ConcertController {


    private final ConcertService concertService;

    @RequestMapping("/concertList")
    public ResponseEntity<ResponseTemplate> concertList(@RequestBody BoardInfoDto bInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        log.info("sort = {} page = {} search = {}", bInfo.getSort(), bInfo.getPage(),bInfo.getSearch());
        Pageable pageable = PageRequest.of(bInfo.getPage() - 1, 8, Sort.by(Sort.Direction.DESC, "concertNo"));
        ConcertListDto result = concertService.concertList(pageable);

        response.setData(result);
        response.setStatus(StatusEnum.SUCCESS);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
