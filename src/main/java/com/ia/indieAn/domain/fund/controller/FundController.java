package com.ia.indieAn.domain.fund.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.fund.dto.FundListDto;
import com.ia.indieAn.domain.fund.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/fund")
@CrossOrigin
public class FundController {

    @Autowired
    FundService fundService;

    @RequestMapping("/allList")
    public ResponseEntity<ResponseTemplate> selectAllFund(@RequestParam(value = "value")String value, @RequestParam(value = "sort")String sort, @RequestParam(value = "standard")String standard){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        Page<FundListDto> fundListDtos = fundService.selectAllFund(value, sort, standard);
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(fundListDtos.getContent());

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
