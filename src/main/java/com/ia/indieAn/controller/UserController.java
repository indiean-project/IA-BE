package com.ia.indieAn.controller;

import com.ia.indieAn.model.Member;
import com.ia.indieAn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/login")
    public Member loginUser(@RequestBody Member member){

        System.out.println(member);
        Member result = userService.loginUser(member);

        System.out.println(result);

        return result;
    }
}
