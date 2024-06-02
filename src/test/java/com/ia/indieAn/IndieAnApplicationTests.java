package com.ia.indieAn;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
import com.ia.indieAn.domain.artist.repository.ArtistRepository;
import com.ia.indieAn.domain.board.repository.*;

import com.ia.indieAn.domain.concert.repository.ConcertRepository;
import com.ia.indieAn.domain.fund.repository.FundRepository;
import com.ia.indieAn.domain.fund.repository.OrderLogRepository;
import com.ia.indieAn.domain.fund.repository.RewardRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.artist.Artist;
import com.ia.indieAn.entity.board.*;

import com.ia.indieAn.entity.concert.Concert;
import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.entity.fund.OrderLog;
import com.ia.indieAn.entity.fund.Reward;
import com.ia.indieAn.entity.user.Member;

import com.ia.indieAn.type.enumType.*;


import com.ia.indieAn.type.enumType.ContentTypeEnum;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import com.ia.indieAn.type.enumType.UserRoleEnum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;

@SpringBootTest
class IndieAnApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ConcertRepository concertRepository;

	@Autowired
	OrderLogRepository orderLogRepository;

	@Autowired
	FundRepository fundRepository;

	@Autowired
	RewardRepository rewardRepository;

	@Autowired
	BoardRepository boardRepository;

	@Autowired
	ColoBoardRepository coloBoardRepository;

	@Autowired
	ColoLogRepository coloLogRepository;

	@Autowired
	ContentLikeLogRepository contentLikeLogRepository;
	@Autowired
	ArtistRepository artistRepository;

	@Autowired
	NoticeRepository noticeRepository;

	@Test
	void contextLoads() throws Exception {
//		for (int i = 2; i < 20; i++) {
//			Member member = new Member();
//			member.setUserId("comet2667" + i + "@naver.com");
//			member.setUserPwd("aA!123123" + i);
//			member.setUserName("박혜성" + i);
//			member.setNickname("옥암동불꽃낙지" + i);
//			member.setPhone("010770526" + i);
//			member.setUserRole(UserRoleEnum.ARTIST);
//			userRepository.save(member);
//		}

		String content = "<p>\n" +
				"    반갑습니다.\n" +
				"</p>\n" +
				"<div>이것은 펀딩 본문 테스트용 html입니다.</div>\n" +
				"<b>나는 박혜성입니다.</b>\n" +
				"<ul>\n" +
				"    <li>펀딩?</li>\n" +
				"    <li>펀딩?</li>\n" +
				"    <li>펀딩?</li>\n" +
				"    <li>펀딩?</li>\n" +
				"    <li>펀딩?</li>\n" +
				"    <li>펀딩?</li>\n" +
				"    <li>펀딩?</li>\n" +
				"</ul>";
		for (int i = 1; i < 20; i++) {
			Fund fund = new Fund();
			fund.setMember(userRepository.findByUserNo(i));
			fund.setFundTypeNo(FundTypeEnum.CONCERT);
			fund.setFundTitle("이것은 펀딩이다 이말이여" + i);
			fund.setStartDate(Date.valueOf("2024-05-10"));
			fund.setEndDate(Date.valueOf("2024-06-10"));
			fund.setPaymentDate(Date.valueOf("2024-06-11"));
			fund.setFundInfo(content);
			fund.setArtistInfo(content);
			fund.setRewardInfo(content);
			fund.setBudgetManage(content);
			fund.setSchedule(content);
			fund.setFundDescription("가나다라");
			fund.setFundStatus(FundStatusEnum.APPROVAL);

			Fund fundResult = fundRepository.save(fund);

			Reward reward1 = new Reward();
			reward1.setFund(fundResult);
			reward1.setRewardName("리워드1");
			reward1.setRewardPrice(50000);
			reward1.setRewardInfo("대단하지 않은 보상");
			reward1.setLimitYn("Y");
			reward1.setLimitAmount(60);

			rewardRepository.save(reward1);

			Reward reward2 = new Reward();
			reward2.setFund(fundResult);
			reward2.setRewardName("리워드2");
			reward2.setRewardPrice(50000);
			reward2.setRewardInfo("대단하지 않은 보상");
			reward2.setLimitYn("N");

			rewardRepository.save(reward2);

			Reward reward3 = new Reward();
			reward3.setFund(fundResult);
			reward3.setRewardName("리워드3");
			reward3.setRewardPrice(50000);
			reward3.setRewardInfo("대단하지 않은 보상");
			reward3.setLimitYn("N");

			rewardRepository.save(reward3);
		}

	}
}