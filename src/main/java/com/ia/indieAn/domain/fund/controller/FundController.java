package com.ia.indieAn.domain.fund.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.fund.dto.FundDetailDto;
import com.ia.indieAn.domain.fund.dto.FundListDto;
import com.ia.indieAn.domain.fund.dto.FundSearchDto;
import com.ia.indieAn.domain.fund.service.FundService;
import kr.co.bootpay.Bootpay;
import kr.co.bootpay.model.request.SubscribePayload;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/fund")
@CrossOrigin
public class FundController {

    @Autowired
    FundService fundService;

    @Value("${bootPay.rest-application-id}")
    private String bootPay_key;

    @Value("${bootPay.private-key}")
    private String private_key;

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

    @RequestMapping("/order")
    public ResponseEntity<ResponseTemplate> orderReward() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        Bootpay bootpay = new Bootpay(bootPay_key, private_key);
        bootpay.getAccessToken();
        HashMap res = null;
        try {
            res = bootpay.lookupBillingKey("ReceipId//변경예정");
            JSONObject json = new JSONObject(res);
            if (res.get("error_code") == null){
                System.out.println("성공" + res);
            } else {
                System.out.println("실패" + res);
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        SubscribePayload payload = new SubscribePayload();
        payload.billingKey = (String)res.get("billing_key");
        payload.orderName = "펀딩";
        payload.price = 1000;
        payload.orderId = "123456";

        Date now = new Date();
        now.setTime(now.getTime() + 20 * 1000); //20초 뒤 결제
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        payload.reserveExecuteAt = sdf.format(now);

        try {
            HashMap postRes = bootpay.reserveSubscribe(payload);
            JSONObject postJson = new JSONObject(postRes);
            System.out.println("예약결제" + postJson);

            if (postRes.get("error_code") == null){
                System.out.println("예약결제 성공" + postRes);
            } else {
                System.out.println("예약결제 실패" + postRes);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
