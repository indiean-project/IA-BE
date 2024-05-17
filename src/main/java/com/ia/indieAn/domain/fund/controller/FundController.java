package com.ia.indieAn.domain.fund.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.fund.dto.FundDetailDto;
import com.ia.indieAn.domain.fund.dto.FundListDto;
import com.ia.indieAn.domain.fund.dto.FundSearchDto;
import com.ia.indieAn.domain.fund.dto.OrderReserveDto;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
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
    public ResponseEntity<ResponseTemplate> orderReward(@RequestBody OrderReserveDto orderReserveDto) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        Fund fund = fundService.selectFund(orderReserveDto.getFundNo());

        Bootpay bootpay = new Bootpay(bootPay_key, private_key);
        bootpay.getAccessToken();
        HashMap res = null;
        try {
            res = bootpay.lookupBillingKey(orderReserveDto.getReceiptId());
            JSONObject json = new JSONObject(res);
            if (res.get("error_code") == null){
            } else {
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

        SimpleDateFormat trans = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDate = trans.parse(String.valueOf(fund.getEndDate()));
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        cal.add(Calendar.DATE, 1);

        Date paymentDate = new Date(cal.getTimeInMillis());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        payload.reserveExecuteAt = sdf.format(paymentDate);

        try {
            HashMap postRes = bootpay.reserveSubscribe(payload);
            JSONObject postJson = new JSONObject(postRes);
            if (postRes.get("error_code") == null){
                //성공

            } else {
                //실패
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
