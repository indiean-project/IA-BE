package com.ia.indieAn.domain.fund.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.fund.dto.FundListDto;
import com.ia.indieAn.domain.fund.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<ResponseTemplate> selectAllFund() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        ArrayList<FundListDto> fundListDtos = fundService.selectAllFund();
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(fundListDtos);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
