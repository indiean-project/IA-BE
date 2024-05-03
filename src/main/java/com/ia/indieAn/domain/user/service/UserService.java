package com.ia.indieAn.domain.user.service;

import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Member loginUser(Member member) {

        Member result = userRepository.findByUserIdAndUserPwd(member.getUserId(), member.getUserPwd());

        return result;
    }

    public Member signUpUser(Member member){
        System.out.println(member);
        return userRepository.save(member);
    }
}
