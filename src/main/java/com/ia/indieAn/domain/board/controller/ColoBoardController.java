package com.ia.indieAn.domain.board.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.board.dto.ColoBoardDto;
import com.ia.indieAn.domain.board.service.ColoBoardService;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.BoardColo;
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
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/board/colo")
@CrossOrigin
public class ColoBoardController {

    @Autowired
    ColoBoardService coloBoardService;

    @RequestMapping("enroll")
    public ResponseEntity<ResponseTemplate> coloBoardEnroll(@RequestBody BoardColo boardColo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        coloBoardService.coloBoardEnroll(boardColo);
        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("boardlist")
    public ResponseEntity<ResponseTemplate> coloBoardList(int page, @RequestBody String sort) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        Pageable pageable = PageRequest.of(page-1, 5, Sort.by(Sort.Direction.DESC, sort.replaceAll("\"","")));

        ArrayList<ColoBoardDto> list = coloBoardService.coloBoardList(pageable, "N");

        response.setData(list);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}