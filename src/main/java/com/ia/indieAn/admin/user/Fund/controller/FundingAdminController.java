package com.ia.indieAn.admin.user.Fund.controller;


import com.ia.indieAn.admin.user.Fund.dto.FundingAdminSearchDto;
import com.ia.indieAn.admin.user.Fund.dto.FundingAdminUserDto;
import com.ia.indieAn.admin.user.Fund.service.FundingAdminService;
import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.type.enumType.FundStatusEnum;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FundingAdminController {

    @Autowired
    FundingAdminService fundingService;

    @ResponseBody
    @RequestMapping("/fundList")
    public ResponseEntity<ResponseTemplate> fundList(){
        log.info("fundList ~~~~~~~~~~~~~~ {}", "테스트");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        ArrayList<FundingAdminUserDto> result = fundingService.selectAllFundList();
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }
    @ResponseBody
    @RequestMapping("/fund/updateStatus")
    public ResponseEntity<ResponseTemplate> fundUpdateStatus(@RequestBody FundingAdminUserDto fundingAdminUserDto){
        log.info("fundUpdateStatus ~~~~~~~~~~{}" , "테스트");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        FundStatusEnum fundStatus = fundingAdminUserDto.getFundStatus();
        int fundNo = fundingAdminUserDto.getFundNo();

        fundingService.updateFundStatus(String.valueOf(fundStatus), fundNo);


        response.setStatus(StatusEnum.SUCCESS);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/fund/searchList")
    public ResponseEntity<ResponseTemplate> fundSearchList(@RequestBody FundingAdminSearchDto fundingAdminSearchDto){
        log.info("fundSearchList ~~~~~~{}" , "테스트");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();


        ArrayList<FundingAdminUserDto> result = fundingService.searchList(fundingAdminSearchDto);
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }




}
