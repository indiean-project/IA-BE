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
	void contextLoads() throws Exception{
		Member admin = new Member();
		admin.setUserId("admin@indiean.com");
		admin.setUserPwd("phs1470!@");
		admin.setUserName("박혜성");
		admin.setNickname("관리자");
		admin.setPhone("01012341234");
		admin.setUserRole(UserRoleEnum.ADMIN);
		userRepository.save(admin);

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
			fund.setFundStatus(FundStatusEnum.AWAIT);


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
		for (int i = 0; i < 2; i++) {
			Fund fund = new Fund();
			fund.setMember(userRepository.findByUserNo(i+1));
			fund.setFundTypeNo(FundTypeEnum.CONCERT);
			fund.setFundTitle("240702 새소년 정기공연 1"+i);
			fund.setFundDescription("Brbrbrbrbr...");
			int random1 = (int)(Math.random() * 10);
			int random2 = (int)(Math.random() * 2) + 1;
			int random3 = (int)(Math.random() * 8) + 1;
			fund.setStartDate(Date.valueOf(String.format("2024-0%d-%d%d", 5, 2, 3)));
			fund.setEndDate(Date.valueOf(String.format("2024-0%d-%d%d", 6, 3, 0)));
			fund.setPaymentDate(Date.valueOf("2024-07-10"));
			fund.setTarget(((int)(Math.random() * 100) + 1) * 100000);
			fund.setFundInfo("테스트용 Html 작성 중");
			fund.setArtistInfo("테스트용 Html 작성 중");
			fund.setRewardInfo("테스트용 Html 작성 중");
			fund.setBudgetManage("테스트용 Html 작성 중");
			fund.setSchedule("테스트용 Html 작성 중");
			fund.setFundStatus(FundStatusEnum.AWAIT);

			Fund fundResult = fundRepository.save(fund);

			Reward reward1 = new Reward();
			reward1.setFund(fundResult);
			reward1.setRewardName("리워드1");
			reward1.setRewardPrice(1000);
			reward1.setRewardInfo("대단하지 않은 보상");
			reward1.setLimitYn("Y");
			reward1.setLimitAmount(60);

			rewardRepository.save(reward1);

			Reward reward2 = new Reward();
			reward2.setFund(fundResult);
			reward2.setRewardName("리워드2");
			reward2.setRewardPrice(1000);
			reward2.setRewardInfo("대단하지 않은 보상");
			reward2.setLimitYn("N");

			rewardRepository.save(reward2);

			Reward reward3 = new Reward();
			reward3.setFund(fundResult);
			reward3.setRewardName("리워드3");
			reward3.setRewardPrice(3000);
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
				orderLog1.setFund(fundRepository.findByFundNo(random1).orElseThrow(()->new CustomException(ErrorCode.FUND_NOT_FOUND)));
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
			concert.setConcertInfo("<p>이런 저런이야기<p>");
			concert.setDeleteYn("N");
			concert.setTicketUrl("https://tickets.interpark.com/goods/24006691");
			concert.setConcertPrice(20000);
			concert.setRuntime("100분");
			concertRepository.save(concert);
		}
//		 자유게시판 및 콜로세움 게시판
//		ArrayList coloNo = new ArrayList<>();
		for(int i = 0; i < 150; i++) {
			Board board = new Board();
			int ran = (int)(Math.random()*100 + 1);
			board.setMember(userRepository.findByUserNo(ran));
			board.setBoardTitle(i + "번째 게시글 제목입니다.");
			board.setBoardContent("대충 게시글 내용입니다.");
			if(i%3 == 1) {
				board.setContentTypeNo(ContentTypeEnum.FREE);
			} else if (i%3 == 2) {
				board.setContentTypeNo(ContentTypeEnum.COLO);
				coloNo.add(i+1);
			} else {
				board.setContentTypeNo(ContentTypeEnum.PROUD);
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
		for(int i = 0; i < coloNo.size(); i++) {
			for(int j = 0; j < 100; j++) {
				ColoLog coloLog = new ColoLog();
				coloLog.setBoardColo(coloBoardRepository.findByColoNo(i+1));
				int ran = (int)(Math.random()*2 + 1);
				coloLog.setVote(ran == 1 ? RlTypeEnum.LEFT : RlTypeEnum.RIGHT);
				coloLog.setMember(userRepository.findByUserNo(j+1));
				coloLogRepository.save(coloLog);
			}
		}
		for(int i = 0; i < 150; i ++) {
			int random = (int)(Math.random()*100 + 0);
			for(int j = 0; j < random; j++) {
				ContentLikeLog contentLikeLog = new ContentLikeLog();
				contentLikeLog.setLikeYn("Y");
				contentLikeLog.setMember(userRepository.findByUserNo(j));
				contentLikeLog.setContentNo(i+1);
				contentLikeLog.setBrType(BrTypeEnum.BOARD);
				contentLikeLogRepository.save(contentLikeLog);
			}

		}
		for(int i = 0; i < 100; i++){
			Artist artist = new Artist();
			artist.setArtistName("아티스트"+i);
			artist.setMusicCategory("이런거 저런거");
			artist.setArtistInfo("<p>\n" +
					"    반갑습니다.\n" +
					"</p>\n" +
					"<div>테스트용 html입니다.</div>\n" +
					"<b>나는 입니다.</b>\n" +
					"<ul>\n" +
					"    <li>설명</li>\n" +
					"    <li>설명</li>\n" +
					"    <li>설명</li>\n" +
					"    <li>설명?</li>\n" +
					"    <li>설명?</li>\n" +
					"    <li>설명?</li>\n" +
					"    <li>설명?</li>\n" +
					"</ul>");
			artist.setMember(userRepository.findByUserNo(i));
			artist.setDebutDate(Date.valueOf("2024-04-05"));
			artist.setArtistStatus("Y");
			artistRepository.save(artist);
		}
		for(int i = 0; i < 50; i++) {
			Notice notice = new Notice();
			notice.setDeleteYn("N");
			notice.setMember(userRepository.findByUserNo(1));
			notice.setNoticeTitle(i + 1 + "번째 공지사항 입니다.");
			notice.setNoticeContent("공지사항 내용입니다.");
			int ran = (int)(Math.random()*500 + 200);
			notice.setViewCount(ran);
			noticeRepository.save(notice);
		}
	}
}