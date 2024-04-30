package com.ia.indieAn.user.controller;

import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
