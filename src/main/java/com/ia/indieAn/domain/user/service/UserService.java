package com.ia.indieAn.domain.user.service;

import com.ia.indieAn.domain.user.dto.LoginUserDto;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    UserRepository userRepository;

    public LoginUserDto loginUser(Member member) {

        Member result = userRepository.findByUserId(member.getUserId())
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        if (!result.getUserPwd().equals(member.getUserPwd())){
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        return new LoginUserDto(result);
    }

    @Transactional(rollbackFor = CustomException.class)
    public void signUpUser(Member member){
        //null 값에 대한 검증은 controller에서
        if (userRepository.existsByUserId(member.getUserId())){
            throw new CustomException(ErrorCode.HAS_ID);
        } else if (userRepository.existsByNickname(member.getNickname())) {
            throw new CustomException(ErrorCode.HAS_NICKNAME);
        } else if (userRepository.existsByPhone(member.getPhone())) {
            throw new CustomException(ErrorCode.HAS_PHONE);
        }
        userRepository.save(member);
    }

    public void checkUserIdNPwd(Member member) {
        if (userRepository.existsByUserId(member.getUserId())){
            throw new CustomException(ErrorCode.HAS_ID);
        }
        userRepository.findByUserId(member.getUserId());
    }
}
