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
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

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

        ConcertListDto result = concertService.concertList(bInfo);
        response.setData(result);
        response.setStatus(StatusEnum.SUCCESS);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/calendarList")
    public ResponseEntity<ResponseTemplate> calendarList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        Date firstDate = Date.valueOf(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
        Date LastDate = Date.valueOf(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));

        List<ConcertDto> result = concertService.calendarList(firstDate,LastDate);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}

