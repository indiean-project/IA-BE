package com.ia.indieAn.admin.user.controller;


import com.ia.indieAn.admin.user.dto.FundingAdminSearchOptionDto;
import com.ia.indieAn.admin.user.dto.FundingAdminUserDto;
import com.ia.indieAn.admin.user.service.FundingAdminService;
import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.entity.fund.Fund;
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
    @RequestMapping("/fundSearch")
    public ResponseEntity<ResponseTemplate> fundSearch(@RequestBody FundingAdminSearchOptionDto fundingAdminSearchOptionDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        ArrayList<FundingAdminUserDto> result = fundingService.selectFundListByUserId(fundingAdminSearchOptionDto);
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }


}
