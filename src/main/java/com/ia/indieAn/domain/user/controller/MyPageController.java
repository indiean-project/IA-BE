package com.ia.indieAn.domain.user.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.user.dto.*;
import com.ia.indieAn.domain.user.service.UserService;
import com.ia.indieAn.entity.user.Member;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin
@Slf4j
public class MyPageController {

    @Autowired
    UserService userService;

//    @Value("${savePath}")
//    private String savePath;
//
//    @Value("${userPath}")
//    private String userPath;

    @Value("${oldUrl}")
    private String oldUrl;

    @Value("${newUrl}")
    private String newUrl;

    @ResponseBody
    @RequestMapping("/myPage")
    public ResponseEntity<ResponseTemplate> userPage(@RequestBody Member member){
        System.out.println(member);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        UserPageDto result = userService.userPageInfo(member);
        System.out.println(result);
        if(result != null) {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(result);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            response.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
        }

    }

    @ResponseBody
    @RequestMapping("/myPage/update")
    public ResponseEntity<ResponseTemplate> updateUser(@RequestBody UpdatePageDto result){

        System.out.println(result.getUserNo());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        userService.updateUser(result);

        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping("/myPage/delete")
    public ResponseEntity<ResponseTemplate> deleteUser(@RequestBody DeleteUserDto result){

        log.info("정보 입력 받기 {}", result);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        userService.deleteUser(result);

        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping("/myPage/board")
    public ResponseEntity<ResponseTemplate> userBoardHistory(@RequestParam(name="userNo") int userNo){
        System.out.println(userNo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        List<UserBoardDto> blist = userService.userBoardHistory(userNo);

        if(blist != null) {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(blist);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            response.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
        }

    }

    @ResponseBody
    @RequestMapping("/myPage/reply")
    public ResponseEntity<ResponseTemplate> userReplyHistory(@RequestParam(name="userNo") int userNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        List<UserReplyDto> rlist = userService.userReplyHistory(userNo);

        if(rlist != null) {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(rlist);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            response.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping("/myPage/fund")
    public ResponseEntity<ResponseTemplate> userFundOrderHistory(@RequestParam(name="userNo") int userNo){
        System.out.println(userNo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        List<UserFundOrderDto> folist = userService.userFundOrderHistory(userNo);

        if(folist != null) {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(folist);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            response.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping("/myPage/fund/reward")
    public ResponseEntity<ResponseTemplate> userRewardOrderHistory(@RequestParam(name="userNo") int userNo,
                                                                   @RequestParam(name="fundNo") int fundNo) {
        System.out.println(userNo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        List<UserRewardOrderDto> rolist = userService.userRewardOrderHistory(userNo, fundNo);

        if(rolist != null) {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(rolist);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            response.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping("/myPage/question")
    public ResponseEntity<ResponseTemplate> userQuestionHistory(@RequestParam(name="userNo") int userNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        List<QuestionSelectDto> qlist = userService.userQuestionHistory(userNo);

        if (qlist != null) {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(qlist);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            response.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping("/myPage/report")
    public ResponseEntity<ResponseTemplate> userReportHistory(@RequestParam(name="userNo") int userNo){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        List<ReportSelectDto> reportList = userService.userReportHistory(userNo);
        if(reportList != null) {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(reportList);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            response.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
        }
    }

}
