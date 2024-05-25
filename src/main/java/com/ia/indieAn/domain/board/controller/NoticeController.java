package com.ia.indieAn.domain.board.controller;

import com.ia.indieAn.common.pageDto.BoardInfoDto;
import com.ia.indieAn.common.pageDto.ListDto;
import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.board.dto.NoticeDto;
import com.ia.indieAn.domain.board.service.NoticeService;
import com.ia.indieAn.entity.board.Notice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@Slf4j
@RestController
@RequestMapping("/api/notice")
@CrossOrigin

public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @RequestMapping("list")
    public ResponseEntity<ResponseTemplate> noticeList(@RequestBody BoardInfoDto boardInfoDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        Pageable pageable = PageRequest.of(boardInfoDto.getPage()-1, 10, Sort.by(Sort.Direction.DESC, boardInfoDto.getSort()));

        ListDto listDto = noticeService.noticeList(pageable, boardInfoDto.getKeyword());

        response.setStatus(StatusEnum.SUCCESS);
        response.setData(listDto);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("detail")
    public ResponseEntity<ResponseTemplate> noticeDetail(@RequestBody long noticeNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        NoticeDto nDto = noticeService.noticeDetail((int)noticeNo);

        response.setStatus(StatusEnum.SUCCESS);
        response.setData(nDto);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
