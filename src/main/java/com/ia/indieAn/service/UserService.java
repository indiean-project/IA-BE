package com.ia.indieAn.service;

import com.ia.indieAn.model.Member;
import com.ia.indieAn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Member loginUser(Member member) {

        Member result = userRepository.findByUserIdAndUserPwd(member.getUserId(), member.getUserPwd());

        return result;
    }
}
