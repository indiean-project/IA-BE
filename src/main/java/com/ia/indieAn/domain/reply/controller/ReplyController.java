package com.ia.indieAn.domain.reply.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.reply.dto.ReplyDto;
import com.ia.indieAn.domain.reply.service.ReplyService;
import com.ia.indieAn.entity.board.Reply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/reply")
@CrossOrigin
public class ReplyController {
    @Autowired
    ReplyService replyService;

    @RequestMapping("list")
    public ResponseEntity<ResponseTemplate> replyList(@RequestBody int contentNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        ArrayList<ReplyDto> listDto = replyService.replyList(contentNo);

        response.setStatus(StatusEnum.SUCCESS);
        response.setData(listDto);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("enroll")
    public ResponseEntity<ResponseTemplate> replyEnroll(@RequestBody Reply reply) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        replyService.replyEnroll(reply);

        response.setStatus(StatusEnum.SUCCESS);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("delete")
    public ResponseEntity<ResponseTemplate> replyDelete(@RequestBody int contentNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        replyService.replyDelete(contentNo);

        response.setStatus(StatusEnum.SUCCESS);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("update")
    public ResponseEntity<ResponseTemplate> replyUpdate(@RequestBody Reply reply) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        replyService.replyUpdate(reply);

        response.setStatus(StatusEnum.SUCCESS);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
