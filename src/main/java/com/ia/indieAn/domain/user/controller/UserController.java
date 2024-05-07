package com.ia.indieAn.domain.user.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.user.dto.LoginUserDto;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.domain.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/apis/user")
@CrossOrigin
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
    @RequestMapping("/signUp/checkInfo")
    public ResponseEntity<ResponseTemplate> checkUserIdNPwd(@RequestBody @Valid Member member) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        userService.checkUserIdNPwd(member);
        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }
}
