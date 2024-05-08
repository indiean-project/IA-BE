package com.ia.indieAn.admin.user.controller;


import com.ia.indieAn.admin.user.service.FundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class FundingController {

    @Autowired
    FundingService fundingService;

    @ResponseBody
    @RequestMapping("login")



}
