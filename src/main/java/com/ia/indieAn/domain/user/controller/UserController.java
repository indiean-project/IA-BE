package com.ia.indieAn.domain.user.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.config.email.EmailService;
import com.ia.indieAn.domain.user.dto.FindUserIdDto;
import com.ia.indieAn.domain.user.dto.LoginUserDto;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    private final EmailService emailService;
    public UserController(EmailService emailService) {
        this.emailService = emailService;
    }

    @ResponseBody
    @RequestMapping("/login")
    public ResponseEntity<ResponseTemplate> loginUser(@RequestBody Member member){
        System.out.println(member);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        LoginUserDto result = userService.loginUser(member);
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
    @RequestMapping("/find/userId")
    public ResponseEntity<ResponseTemplate> findUserId(@RequestBody Member member){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        FindUserIdDto findUserId = userService.checkPhone(member);
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(findUserId);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

//    @ResponseBody
//    @RequestMapping("/find/userPwd")
//    public ResponseEntity<ResponseTemplate> findUserPwd(@RequestBody Member member){
//        System.out.println(member);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        ResponseTemplate response = new ResponseTemplate();
//
//        FindUserIdDto findUserId = userService.checkPhone(member);
//        log.info("member객체찍어보기{}", findUserId);
//        response.setStatus(StatusEnum.SUCCESS);
//        response.setData(findUserId);
//
//        return new ResponseEntity<>(response, headers, HttpStatus.OK);
//    }


    @ResponseBody
    @RequestMapping("/signUp")
    public ResponseEntity<ResponseTemplate> signUpUser(@RequestBody Member member){
        log.info("enter /signUp {}", member);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        userService.signUpUser(member);
        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/signUp/checkId")
    public ResponseEntity<ResponseTemplate> checkUserId(@RequestBody @Valid Member member) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        userService.checkUserId(member);
        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping("/signUp/checkPwd")
    public ResponseEntity<ResponseTemplate> checkUserPwd(@RequestBody @Valid Member member) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping("/signUp/sendEmail")
    public String sendVerifyEmail (@RequestParam("userId") String userId) throws Exception {
        log.info("인증아이디 : {}", userId);

        String code = emailService.sendVerifyMessage(userId);
        log.info("인증코드 : {}", code);
        return code;
    }

    // 백엔드에서도 인증 번호 입력 확인해야할 method 필요함
    // 아이디, 비밀번호 찾기 및 제공 method도 필요함

}

