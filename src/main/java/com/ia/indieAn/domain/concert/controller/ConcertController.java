package com.ia.indieAn.domain.concert.controller;

import com.ia.indieAn.common.pageDto.BoardInfoDto;
import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.concert.dto.*;
import com.ia.indieAn.common.pageDto.ListDto;
import com.ia.indieAn.domain.concert.service.ConcertService;

import com.ia.indieAn.entity.concert.Concert;
import com.ia.indieAn.entity.concert.ConcertReply;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/concert")
@CrossOrigin
@RequiredArgsConstructor
public class ConcertController {


    private final ConcertService concertService;
    @Value("${oldUrl}")
    private String oldUrl;

    @Value("${newUrl}")
    private String newUrl;

    @RequestMapping("/concertList")
    public ResponseEntity<ResponseTemplate> concertList(@RequestBody BoardInfoDto bInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        ListDto result = concertService.concertList(bInfo);
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

        List<ConcertDto> result = concertService.calendarList(firstDate, LastDate);

        response.setData(result);
        response.setStatus(StatusEnum.SUCCESS);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/detail")
    public ResponseEntity<ResponseTemplate> concertDetail(@RequestParam(value = "concertNo") int concertNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        ConcertDetailDto result = concertService.concertDetail(concertNo);
        response.setData(result);
        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/replyList")
    public ResponseEntity<ResponseTemplate> concertReplyList(@RequestParam(value = "concertNo") int concertNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        List<ConcertReplyListDto> result = concertService.cocnertReplyList(concertNo);

        if (result != null) {
            response.setData(result);
            response.setStatus(StatusEnum.SUCCESS);
        } else {
            response.setData(result);
            response.setStatus(StatusEnum.FAIL);
        }


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/addReply")
    public ResponseEntity<ResponseTemplate> concertAddReply(@RequestBody ConcertReplyDto concertReplyDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        ConcertReply result = concertService.concertAddReply(concertReplyDto);
        if (result != null) {
            response.setStatus(StatusEnum.SUCCESS);
        } else {
            response.setStatus(StatusEnum.FAIL);
        }
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/deleteReply")
    public ResponseEntity<ResponseTemplate> concertDeleteReply(@RequestBody int contentNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        ConcertReply result = concertService.concertDeleteReply(contentNo);
        if (result != null) {
            response.setStatus(StatusEnum.SUCCESS);
        } else {
            response.setStatus(StatusEnum.FAIL);
        }
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/updateReply")
    public ResponseEntity<ResponseTemplate> concertUpdateReply(@RequestBody ConcertReplyDto concertReplyDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        ConcertReply result = concertService.concertUpdateReply(concertReplyDto);
        if (result != null) {
            response.setStatus(StatusEnum.SUCCESS);
        } else {
            response.setStatus(StatusEnum.FAIL);
        }
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    @RequestMapping("/enroll")
    public ResponseEntity<ResponseTemplate> concertEnroll(@RequestBody ConcertEnrollDto concert) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        concert.setConcertInfo(concert.getConcertInfo().replace(oldUrl, newUrl));

        Concert result = concertService.concertEnroll(concert);

        response.setData(result.getConcertNo());
        response.setStatus(StatusEnum.SUCCESS);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


}

