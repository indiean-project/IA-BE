package com.ia.indieAn.admin.user.controller;


import com.ia.indieAn.admin.user.dto.AuthorityAdminDto;
import com.ia.indieAn.admin.user.service.AuthorityAdminService;
import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin

public class AuthorityAdminController {

    @Autowired
    AuthorityAdminService authorityAdminService;

    @ResponseBody
    @RequestMapping("/userAuthority")
    public ResponseEntity<ResponseTemplate> userAuthority() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        ArrayList<AuthorityAdminDto> result = authorityAdminService.selectAllAuthorityList();
        for(int i=0; i<result.size(); i++) {
            System.out.println(result.get(i));
        }

        response.setStatus(StatusEnum.SUCCESS);
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping("/userAuthority/title/{title}")
    public ResponseEntity<ResponseTemplate> userAuthorityTitle(String title) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        ArrayList<AuthorityAdminDto> result = authorityAdminService.selectAllAuthorityList();
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping("/userAutority/type/{type}")
    public ResponseEntity<ResponseTemplate> userAuthorityType(String type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        ArrayList<AuthorityAdminDto> result = authorityAdminService.selectAllAuthorityList();
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);


    }

    @ResponseBody
    @RequestMapping("/userAutority/status/{status}")
    public ResponseEntity<ResponseTemplate> userAuthorityStatus(String status) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        ArrayList<AuthorityAdminDto> result = authorityAdminService.selectAllAuthorityList();
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);


    }

}
