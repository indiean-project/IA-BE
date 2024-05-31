package com.ia.indieAn.admin.user.controller;

import com.ia.indieAn.admin.user.dto.FundingAdminUserDto;
import com.ia.indieAn.admin.user.dto.QuestionAdminDto;
import com.ia.indieAn.admin.user.dto.QuestionAdminSearchDto;
import com.ia.indieAn.admin.user.service.QuestionAdminService;
import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin

public class QuestionAdminController {

    private static final Logger log = LoggerFactory.getLogger(QuestionAdminController.class);
    @Autowired
    QuestionAdminService questionService;



    @ResponseBody
    @RequestMapping("/question/list")
    public ResponseEntity<ResponseTemplate> questionList(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        ArrayList<QuestionAdminDto> result = questionService.selectAllQuestionList();
        log.info("result={}",result);
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/question/updateQuestion")
    public ResponseEntity<ResponseTemplate> updateQuestion(@RequestBody QuestionAdminDto questionAdminDto){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();


        questionService.updateQuestion(questionAdminDto.getQuestionNo(), questionAdminDto.getAnsContent());
        ArrayList<QuestionAdminDto> result = questionService.selectAllQuestionList();
       response.setStatus(StatusEnum.SUCCESS);
       response.setData(result);

       return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/question/questionSearchList")
    public ResponseEntity<ResponseTemplate> questionSearchList(@RequestBody QuestionAdminSearchDto questionAdminSearchDto){
        System.out.println("fggfgfg");
        log.info("questionSearchList ~~~~~~{}" , "테스트");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        System.out.println(questionAdminSearchDto);


        ArrayList<QuestionAdminDto> result = questionService.searchQuestionList(questionAdminSearchDto);
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }




}
