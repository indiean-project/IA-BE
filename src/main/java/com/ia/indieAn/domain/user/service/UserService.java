package com.ia.indieAn.domain.user.service;

import com.ia.indieAn.config.email.EmailService;
import com.ia.indieAn.domain.user.dto.LoginUserDto;
import com.ia.indieAn.domain.user.dto.UpdatePageDto;
import com.ia.indieAn.domain.user.dto.UserPageDto;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    private final EmailService emailService;


    public LoginUserDto loginUser(Member member) {

        Member result = userRepository.findByUserId(member.getUserId())
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!result.getSocialStatus().equals("N") && result.getUserPwd() == null) {
            // getUserPwd가 null인 경우는 socialStatus != "N"인 경우뿐이므로 빠른 return 가능
            return new LoginUserDto(result);
        }

        if (result.getSocialStatus().equals("N")
                && !result.getUserPwd().equals(member.getUserPwd())){
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        return new LoginUserDto(result);
    }

    @Transactional(rollbackFor = CustomException.class)
    public void signUpUser(Member member){
        //null 값에 대한 검증은 controller에서
//        if (userRepository.existsByUserId(member.getUserId())){
//            throw new CustomException(ErrorCode.HAS_ID);
//        } else if (userRepository.existsByNickname(member.getNickname())) {
//            throw new CustomException(ErrorCode.HAS_NICKNAME);
//        } else if (userRepository.existsByPhone(member.getPhone())) {
//            throw new CustomException(ErrorCode.HAS_PHONE);
//        }
        // 이미 객체쪽에서 유효성 검사를 하기에, 전화번호 외에는 별도로 하지 않는다.
        // 랜덤 닉네임 개체를 저장하는게 필요하다. -> 여기서 조회도 필요하긴 하다. 랜덤이긴 하겠지만은.
        log.info("enter {}", member);
        if (!member.getSocialStatus().equals("N")) {
            member.setUserPwd("");
        } else {
            if (userRepository.existsByPhone(member.getPhone())) {
                throw new CustomException(ErrorCode.HAS_PHONE);
            }
        }
        String uniqueNickname = generateUniqueNickname();
        member.setNickname(uniqueNickname);

        log.info("222222 {}", member);
        userRepository.save(member);
    }

    @Transactional(rollbackFor = CustomException.class)
    public void checkUserId(Member member) {
        if (userRepository.existsByUserId(member.getUserId())){
            throw new CustomException(ErrorCode.HAS_ID);
        }
    }

    private String generateUniqueNickname() {
        Random random = new Random();
        String adjective = randomAdjective();
        String centralName = "인디안";
        String nickname = adjective + centralName + random.nextInt(1000);

        while (userRepository.existsByNickname(nickname)) {
            adjective = randomAdjective();
            nickname = adjective + centralName + random.nextInt(1000);
        }

        return nickname;
    }

    public static String randomAdjective() {
        List<String> adjectives = Arrays.asList("강렬한", "신나는", "따뜻한", "감성의",
                                                "끌리는", "행복한", "멋진", "즐거운");
        Collections.shuffle(adjectives);
        return adjectives.get(0);
    }

    public UserPageDto userPageInfo(Member member) {

        Member result = userRepository.findByNickname(member.getNickname());

        return new UserPageDto(result);
    }

    @Transactional(rollbackFor = CustomException.class)
    public void updateUser(UpdatePageDto result) {
        //null 값에 대한 검증은 controller에서
        Member mem = userRepository.findByUserNo(result.getUserNo());

        if (userRepository.existsByNicknameAndUserNoNot(result.getNickname(), mem.getUserNo())) {
            throw new CustomException(ErrorCode.HAS_NICKNAME);
        } else if (userRepository.existsByPhoneAndUserNoNot(result.getPhone(), mem.getUserNo())) {
            throw new CustomException(ErrorCode.HAS_PHONE);
        }

        mem.setNickname(result.getNickname());
        mem.setPhone(result.getPhone());
        mem.setUserContent(result.getUserContent());
        mem.setAddress(result.getAddress());
    }
}
