package com.ia.indieAn.domain.board.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.board.dto.FreeBoardDto;
import com.ia.indieAn.domain.board.service.FreeBoardService;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/board/free")
@CrossOrigin
@Slf4j
public class FreeBoardController {

    @Autowired
    FreeBoardService boardService;

    @RequestMapping("/enroll")
    public ResponseEntity<ResponseTemplate> freeBoardEnroll(@RequestBody Board board) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        board.setContentTypeNo(ContentTypeEnum.FREE);

        if (board.getBoardTitle() == null) {
            log.info("TitleNullException : {} ", board.getBoardTitle());
            response.setStatus(StatusEnum.FAIL);
        } else if (board.getBoardContent() == null) {
            log.info("ContentNullException : {} ", board.getBoardContent());
            response.setStatus(StatusEnum.FAIL);
        } else if (board.getMember() == null) {
            log.info("UserNoNullException : {} ", board.getMember());
            response.setStatus(StatusEnum.FAIL);
        }

        Board b = boardService.boardEnroll(board);
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(b.getBoardNo());
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/boardlist")
    public ResponseEntity<ResponseTemplate> freeBoardList(@PageableDefault(sort = "boardNo", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        List<FreeBoardDto> list = boardService.freeBoardList(pageable);
        response.setData(list);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
