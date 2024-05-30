package com.ia.indieAn.domain.board.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.board.dto.BoardDto;
import com.ia.indieAn.domain.board.service.BoardService;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.ContentLikeLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/api/board")
@CrossOrigin
public class BoardController {

    @Autowired
    BoardService boardService;

    @Value("${oldUrl}")
    private String oldUrl;

    @Value("${newUrl}")
    private String newUrl;

    @RequestMapping("/enroll")
    public ResponseEntity<ResponseTemplate> freeBoardEnroll(@RequestBody Board board) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        String changeUrl = board.getBoardContent().replace(oldUrl, newUrl);
        board.setBoardContent(changeUrl);
        Board b = boardService.boardEnroll(board);

        ArrayList list = new ArrayList();
        list.add(b.getBoardNo());
        list.add(b.getContentTypeNo());

        response.setStatus(StatusEnum.SUCCESS);
        response.setData(list);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/viewCount")
    public ResponseEntity<ResponseTemplate> viewCount(@RequestBody int boardNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        boardService.viewCount(boardNo);

        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/likeCount")
    public ResponseEntity<ResponseTemplate> likeCount(@RequestBody ContentLikeLog contentLikeLog) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        boardService.likeCount(contentLikeLog);

        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/amount")
    public ResponseEntity<ResponseTemplate> boardAmount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        ArrayList amountList = boardService.boardAmount();

        response.setStatus(StatusEnum.SUCCESS);
        response.setData(amountList);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/delete")
    public ResponseEntity<ResponseTemplate> boardDelete(@RequestBody Board board) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        boardService.boardDelete(board);

        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/detail")
    public ResponseEntity<ResponseTemplate> boardDetail(@RequestBody long boardNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        BoardDto b = boardService.boardDetail((int)boardNo);

        response.setStatus(StatusEnum.SUCCESS);
        response.setData(b);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
