package com.ia.indieAn;

import com.ia.indieAn.domain.fund.repository.FundRepository;
import com.ia.indieAn.domain.fund.repository.OrderLogRepository;
import com.ia.indieAn.domain.fund.repository.RewardRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.entity.fund.OrderLog;
import com.ia.indieAn.entity.fund.Reward;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import com.ia.indieAn.type.enumType.UserRoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
class IndieAnApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	FundRepository fundRepository;

	@Autowired
	RewardRepository rewardRepository;

	@Autowired
	OrderLogRepository orderLogRepository;

	@Test
	void contextLoads() throws Exception{

		for (int i = 0; i < 20; i++) {
			Member member = new Member();
			member.setUserId("comet2667"+i);
			member.setUserPwd("123123"+i);
			member.setUserName("박혜성"+i);
			member.setNickname("옥암동불꽃낙지"+i);
			member.setPhone("0107705266"+i);
			member.setUserRole(UserRoleEnum.ARTIST);
			userRepository.save(member);
		}
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
		for (int i = 0; i < 20; i++) {
			Fund fund = new Fund();
			fund.setMember(userRepository.findByUserNo(i+1));
			fund.setFundTypeNo(FundTypeEnum.CONCERT);
			fund.setFundTitle("이것은 펀딩이다 이말이여"+i);
			fund.setFundDescription("펀딩을 약식으로 서술 하겠다 이말이여");
			fund.setStartDate(Date.valueOf("2024-05-10"));
			fund.setEndDate(Date.valueOf("2024-06-10"));
			fund.setPaymentDate(Date.valueOf("2024-06-11"));
			fund.setTarget(10000000 * i);
			fund.setFundInfo(content);
			fund.setArtistInfo(content);
			fund.setRewardInfo(content);
			fund.setBudgetManage(content);
			fund.setSchedule(content);
			fund.setFundStatus("Y");

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
		for (int i = 0; i < 20; i++) {
			int ranNum = (int)(Math.random() * 100) + 1;
			for (int j = 0; j < ranNum; j++) {
				OrderLog orderLog1 = new OrderLog();
				orderLog1.setMember(userRepository.findByUserNo((int)(Math.random()*20) + 1));
				orderLog1.setFund(fundRepository.findByFundNo(i+1));
				orderLog1.setTotalPrice((int)(Math.random()* 100000) + 1);
				orderLog1.setReceiptId("test");
				orderLog1.setBillingKey("test");
				orderLog1.setPaymentDate(orderLog1.getFund().getPaymentDate());
				orderLogRepository.save(orderLog1);
			}
		}
		Member member = new Member();
		member.setUserId("zest@naver.com");
		member.setUserPwd("1q2w3e4r!@");
		member.setUserName("박혜성");
		member.setNickname("대세는제스트");
		member.setPhone("01012349698");
		member.setUserRole(UserRoleEnum.ARTIST);

		Member result = userRepository.save(member);


	}

}
