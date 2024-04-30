package com.ia.indieAn.user.service;

import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.user.repository.UserRepository;
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
}
