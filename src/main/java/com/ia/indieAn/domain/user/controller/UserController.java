package com.ia.indieAn.domain.user.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.user.dto.LoginUserDto;
import com.ia.indieAn.domain.user.dto.UserPageDto;
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
//
import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

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
    @RequestMapping("/signUp")
    public ResponseEntity<ResponseTemplate> signUpUser(@RequestBody Member member){
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
//        log.info("enter /signUp/checkPwd {}", member);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }

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
    public ResponseEntity<ResponseTemplate> updateUser(@RequestBody Member member){
        System.out.println(member);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        userService.updateUser(member);

        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }
}
