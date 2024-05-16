package com.ia.indieAn.domain.fund.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.fund.dto.FundDetailDto;
import com.ia.indieAn.domain.fund.dto.FundListDto;
import com.ia.indieAn.domain.fund.dto.FundSearchDto;
import com.ia.indieAn.domain.fund.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/fund")
@CrossOrigin
public class FundController {

    @Autowired
    FundService fundService;

    @RequestMapping("/allList")
    public ResponseEntity<ResponseTemplate> selectAllFund(@RequestBody FundSearchDto fundSearchDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        if(fundSearchDto.getSearchValue().equals("artist")){
            fundSearchDto.setArtistKeyword(fundSearchDto.getKeyword());
        } else if (fundSearchDto.getSearchValue().equals("fundTitle")) {
            fundSearchDto.setTitleKeyword(fundSearchDto.getKeyword());
        } else if (fundSearchDto.getSearchValue().equals("fundContent")) {
            fundSearchDto.setContentKeyword(fundSearchDto.getKeyword());
        } else if (fundSearchDto.getSearchValue().equals("all")) {
            fundSearchDto.setAllKeyword(fundSearchDto.getKeyword());
        }

        Page<FundListDto> fundListDtos = fundService.selectAllFund(fundSearchDto);
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(fundListDtos.getContent());

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/soonList")
    public ResponseEntity<ResponseTemplate> selectSoonList(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        Page<FundListDto> fundListDtos = fundService.selectSoonFund();
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(fundListDtos.getContent());

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/detail")
    public ResponseEntity<ResponseTemplate> selectFundDetail(@RequestParam(value = "fundNo")int fundNo){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        FundDetailDto fundDetailDto = fundService.selectFundDetail(fundNo);
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(fundDetailDto);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
