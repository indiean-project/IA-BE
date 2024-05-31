package com.ia.indieAn.domain.user.service;

import com.ia.indieAn.config.email.EmailService;
import com.ia.indieAn.domain.user.dto.*;
import com.ia.indieAn.domain.user.repository.QuestionRepository;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
import com.ia.indieAn.entity.user.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    private final EmailService emailService;

    public LoginUserDto loginUser(Member member) {

        Member result = userRepository.findByUserId(member.getUserId())
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!result.getSocialStatus().equals("N") && result.getUserPwd() == null) {
            return new LoginUserDto(result);
        }

        if (result.getSocialStatus().equals("N")
                && !result.getUserPwd().equals(member.getUserPwd())){
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        return new LoginUserDto(result);
    }

    public FindUserIdDto checkPhone(Member member) {
        Member findUserId = userRepository.findByPhone(member.getPhone())
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));

        return new FindUserIdDto(findUserId);
    }

    public boolean findPassword(String userId, String updatePwd) {
        Optional<Member> user = userRepository.findByUserId(userId);
        if (user.isPresent()) {
            Member existingUser = user.get();
            existingUser.setUserPwd(updatePwd);
            userRepository.save(existingUser);
            return true;
        } else {
            return false;
        }
    }


    @Transactional(rollbackFor = CustomException.class)
    public void signUpUser(Member member){
        log.info("enter {}", member);
        if (!member.getSocialStatus().equals("N")) { // socialLogin checking
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
        log.info("이미지 저장 정보{}", result.getUserProfileImg());

        mem.setNickname(result.getNickname());
        mem.setPhone(result.getPhone());
        mem.setUserContent(result.getUserContent());
        mem.setAddress(result.getAddress());
        mem.setUserPwd(result.getUserPwd());
        mem.setUserName(result.getUserName());
        mem.setUserProfileImg(result.getUserProfileImg());
        mem.setUserFavoriteArtist(result.getUserFavoriteArtist());
        mem.setUserFavoriteMusic(result.getUserFavoriteMusic());
    }

    public List<UserBoardDto> userBoardHistory(int userNo) {
        List<UserBoardProjection> boardProjections = userRepository.findUserBoardsByMemberUserNo(userNo);

        return boardProjections.stream()
                .map(UserBoardDto::userBoardHistory)
                .collect(Collectors.toList());

    }

    public List<UserReplyDto> userReplyHistory(int userNo) {
        List<UserReplyProjection> replyProjection = userRepository.findUserRepliesByMemberUserNo(userNo);

        return replyProjection.stream()
                .map(UserReplyDto::userReplyHistory)
                .collect(Collectors.toList());
    }

    public List<UserFundOrderDto> userFundOrderHistory(int userNo) {
        List<UserFundOrderProjection> fundOrderProjections = userRepository.findUserFundOrderByOrderLogUserNo(userNo);
        return fundOrderProjections.stream()
                .map(UserFundOrderDto::fundOrderHistory)
                .collect(Collectors.toList());
    }

    public List<UserRewardOrderDto> userRewardOrderHistory(int userNo, int fundNo) {
        List<UserRewardOrderProjection> rewardOrderProjections = userRepository.findUserRewardOrderByUserNoAndFundNo(userNo, fundNo);
        return rewardOrderProjections.stream()
                .map(UserRewardOrderDto::rewardOrderHistory)
                .collect(Collectors.toList());
    }

    public List<QuestionSelectDto> userQuestionHistory(int userNo) {
        List<QuestionSelectProjection> qsProjection = userRepository.findUserQuestionByUserNo(userNo);

        return qsProjection.stream()
                .map(QuestionSelectDto::questionHistory)
                .collect(Collectors.toList());
    }

    public List<ReportSelectDto> userReportHistory(int userNo) {
        List<ReportSelectProjection> crlProjection = userRepository.findUserReportByUserNo(userNo);

        return crlProjection.stream()
                .map(ReportSelectDto::reportHistory)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = CustomException.class)
    public void enrollQuestion(QuestionEnrollDto qe) {
        log.info("Received question: {}", qe);
        log.info("Received member userNo: {}", qe.getUserNo() != 0 ? qe.getUserNo() : "null");

        if (qe.getUserNo() == 0 ) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        Member member = userRepository.findByUserNo(qe.getUserNo());
        if (member == null) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        log.info("enter {}", member);

        if(qe.getQuestionContent() == null || qe.getQuestionContent().isEmpty()) {
            throw new CustomException(ErrorCode.QUESTION_NULL);
        }
        log.info("enter {}", qe);
        Question q = new Question();
        q.setMember(member);
        q.setQuestionContent(qe.getQuestionContent());

        questionRepository.save(q);
    }
}
