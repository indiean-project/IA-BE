package com.ia.indieAn;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
import com.ia.indieAn.domain.board.repository.BoardRepository;
import com.ia.indieAn.domain.board.repository.ColoBoardRepository;
import com.ia.indieAn.domain.concert.repository.ConcertRepository;
import com.ia.indieAn.domain.concert.repository.ConcertRepository;
import com.ia.indieAn.domain.fund.repository.FundRepository;
import com.ia.indieAn.domain.fund.repository.OrderLogRepository;
import com.ia.indieAn.domain.fund.repository.RewardRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.BoardColo;
import com.ia.indieAn.entity.concert.Concert;
import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.entity.fund.OrderLog;
import com.ia.indieAn.entity.fund.Reward;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import com.ia.indieAn.type.enumType.UserRoleEnum;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
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

	@Test
	void contextLoads() throws Exception{

		for (int i = 0; i < 100; i++) {
			Member member = new Member();
			member.setUserId("comet2667"+i+"@naver.com");
			member.setUserPwd("phs1470!@");
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
		for (int i = 0; i < 100; i++) {
			Fund fund = new Fund();
			fund.setMember(userRepository.findByUserNo(i+1));
			fund.setFundTypeNo(FundTypeEnum.CONCERT);
			fund.setFundTitle("이것은 펀딩이다 이말이여"+i);
			fund.setFundDescription("펀딩을 약식으로 서술 하겠다 이말이여");
			int random1 = (int)(Math.random() * 10);
			int random2 = (int)(Math.random() * 2) + 1;
			int random3 = (int)(Math.random() * 8) + 1;
			fund.setStartDate(Date.valueOf(String.format("2024-0%d-%d%d", random3, random2, random1)));
			fund.setEndDate(Date.valueOf(String.format("2024-0%d-%d%d", random3 + 1, random2, random1)));
			fund.setPaymentDate(Date.valueOf("2024-07-10"));
			fund.setTarget(((int)(Math.random() * 100) + 1) * 10000000);
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
		for (int i = 0; i < 200; i++) {
			int ranNum = (int)(Math.random() * 100) + 1;
			for (int j = 0; j < ranNum; j++) {
				OrderLog orderLog1 = new OrderLog();
				orderLog1.setMember(userRepository.findByUserNo((int)(Math.random()*100) + 1));
				int random1 = (int)(Math.random() * 100) + 1;
				orderLog1.setFund(fundRepository.findByFundNo(random1)
						.orElseThrow(()->new CustomException(ErrorCode.FUND_NOT_FOUND)));
				orderLog1.setTotalPrice((int)(Math.random()* 100000) + 1);
				orderLog1.setReceiptId("test");
				orderLog1.setBillingKey("test");
				orderLog1.setPaymentDate(orderLog1.getFund().getPaymentDate());
				orderLogRepository.save(orderLog1);
			}
		}
		for(int i = 1; i < 20; i++){
			Concert concert = new Concert();
			concert.setConcertNo(i);
			concert.setConcertTitle("타이틀"+i);
			concert.setLocation("주소123");
			concert.setStartDate(Date.valueOf("2024-05-"+(i+1)));
			concert.setEndDate(Date.valueOf("2024-05-"+(i+2)));
			concert.setConcertInfo("이런 저런이야기");
			concert.setDeleteYn("N");
			concertRepository.save(concert);
		}
		// 자유게시판 및 콜로세움 게시판
		ArrayList coloNo = new ArrayList();
		for(int i = 0; i < 100; i++) {
			Board board = new Board();
			board.setMember(userRepository.findByUserNo(i+1));
			board.setBoardTitle(i + "번째 게시글 제목입니다.");
			board.setBoardContent("대충 게시글 내용입니다.");
			if(i%2 == 1) {
				board.setContentTypeNo(ContentTypeEnum.FREE);
			} else {
				board.setContentTypeNo(ContentTypeEnum.COLO);
				coloNo.add(i+1);
			}
			int random = (int)(Math.random()*300 + 100);
			board.setViewCount(random);
			boardRepository.save(board);
		}
		for(int i = 0; i < coloNo.size(); i++) {
			BoardColo boardColo = new BoardColo();
			boardColo.setBoard(boardRepository.findByBoardNo((int)coloNo.get(i)));
			boardColo.setColLeftTitle("양념치킨");
			boardColo.setColRightTitle("후라이드 치킨");
			coloBoardRepository.save(boardColo);
		}
	}
}