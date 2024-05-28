package com.ia.indieAn.domain.fund.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.fund.dto.*;
import com.ia.indieAn.domain.fund.service.FundService;
import com.ia.indieAn.domain.user.service.UserService;
import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.entity.user.Member;
import kr.co.bootpay.Bootpay;
import kr.co.bootpay.model.request.SubscribePayload;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        System.out.println(fundSearchDto);

        Slice<FundListDto> fundListDtos = fundService.selectAllFund(fundSearchDto);
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
    public ResponseEntity<ResponseTemplate> orderReward(@RequestBody OrderReserveDto orderReserveDto) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        System.out.println(orderReserveDto);
        Fund fund = fundService.selectFund(orderReserveDto.getFundNo());

        Bootpay bootpay = new Bootpay(bootPay_key, private_key);
        bootpay.getAccessToken();
        HashMap res = null;
        try {
            res = bootpay.lookupBillingKey(orderReserveDto.getReceiptId());
            JSONObject json = new JSONObject(res);
            if (res.get("error_code") != null){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        SubscribePayload payload = new SubscribePayload();
        payload.billingKey = (String)res.get("billing_key");
        payload.orderName = "리워드 결제";
        payload.price = orderReserveDto.getTotalPrice();
        payload.orderId = orderReserveDto.getFundNo() + "/" + orderReserveDto.getUserNo();

        orderReserveDto.setPaymentDate(fund.getPaymentDate());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        payload.reserveExecuteAt = sdf.format(orderReserveDto.getPaymentDate());

        try {
            HashMap postRes = bootpay.reserveSubscribe(payload);
            JSONObject postJson = new JSONObject(postRes);
            if (postRes.get("error_code") != null){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        fundService.insertOrderLog(fund, orderReserveDto, (String)res.get("billing_key"));

        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/enroll")
    public ResponseEntity<ResponseTemplate> enrollFund(@RequestBody FundEnrollDto fundEnrollDto) throws ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        fundEnrollDto.setFundInfo(fundEnrollDto.getFundInfo().replace("<img src=\"/public/tempImg", "<img src=\"/public/img"));
        fundEnrollDto.setArtistInfo(fundEnrollDto.getArtistInfo().replace("<img src=\"/public/tempImg", "<img src=\"/public/img"));

        int fundNo = fundService.enrollFund(fundEnrollDto);
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(fundNo);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
