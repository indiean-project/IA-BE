package com.ia.indieAn.domain.user.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.user.service.UserService;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.entity.user.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/question")
@CrossOrigin
@Slf4j
public class QuestionController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/enroll")
    public ResponseEntity<ResponseTemplate> enrollQuestion(@RequestBody Question question) {
        log.info("enter /enroll {}, question");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        userService.enrollQuestion(question);
        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}