package com.ia.indieAn;

import com.ia.indieAn.admin.user.repository.FundingAdminUserRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.converter.FundTypeConverter;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import com.ia.indieAn.type.enumType.UserRoleEnum;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

@SpringBootTest
class IndieAnApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	FundingAdminUserRepository fundingAdminUserRepository;


	@Test
	void contextLoads() throws Exception{
//		Member member = new Member();
//		member.setUserId("comet2667");
//		member.setUserPwd("123123");
//		member.setUserName("박혜성");
//		member.setNickname("옥암동불꽃낙지");
//		member.setPhone("01077052667");
//		member.setUserRole(UserRoleEnum.ARTIST);

//		Member result = userRepository.save(member);
//		System.out.println(result);
//
//
//
//		System.out.println();

//		Member member = new Member();
//		member.setUserId("m715800");
//		member.setUserPwd("12345");
//		member.setUserName("이동협");
//		member.setNickname("빵돌이빵순이");
//		member.setPhone("010-8248-8489");
//		member.setAddress("가마산로");
//		member.setUserProfileImg("ㅇㅇ");
//		member.setUserContent("ㅇㅇ");
//		member.setUserFavoriteArtist("윤딴딴");
//		member.setUserFavoriteMusic("겨울을 걷는다");
//		userRepository.save(member);
//		Member result = userRepository.findByUserNo(member.getUserNo());
//
//		Fund fund = new Fund();
//		fund.setMember(result);
//		fund.setFundTypeNo(FundTypeEnum.CONCERT);
//		fund.setFundTitle("제목입니다");
//		fund.setStartDate(Date.valueOf("2024-05-03"));
//		fund.setEndDate(Date.valueOf("2024-05-04"));
//		fund.setPaymentDate(Date.valueOf("2024-05-03"));
//		fund.setFundStatus("Y");
//
//		fundingAdminUserRepository.save(fund);
//
//		System.out.println("========= 1 =============");
///////////////////////////////////////////////////////////////////
		Member member1 = new Member();
		member1.setUserId("m715500");
		member1.setUserPwd("123456");
		member1.setUserName("이동협2");
		member1.setNickname("대혜성");
		member1.setPhone("010-1234-5678");
		member1.setAddress("가마산로1");
		member1.setUserProfileImg("ㅇㅇ1");
		member1.setUserContent("ㅇㅇ1");
		member1.setUserFavoriteArtist("윤딴딴1");
		member1.setUserFavoriteMusic("겨울을 걷는다1");
		userRepository.save(member1);
		Member result1 = userRepository.findByUserNo(member1.getUserNo());


		Fund fund1 = new Fund();
		fund1.setMember(result1);
		fund1.setFundTypeNo(FundTypeEnum.CONCERT);
		fund1.setFundTitle("제목입니다2");
		fund1.setStartDate(Date.valueOf("2024-05-03"));
		fund1.setEndDate(Date.valueOf("2024-05-04"));
		fund1.setPaymentDate(Date.valueOf("2024-05-03"));
		fund1.setFundStatus("Y");

		fundingAdminUserRepository.save(fund1);

		System.out.println("========= 2 =============");
		///////////////////////////////////////////////////////////////////

		Member member2 = new Member();
		member2.setUserId("ddkkt");
		member2.setUserPwd("123457");
		member2.setUserName("대태건");
		member2.setNickname("빵순이빵돌이");
		member2.setPhone("010-4545-5656");
		member2.setAddress("가마산로2");
		member2.setUserProfileImg("ㅇㅇ2");
		member2.setUserContent("ㅇㅇ2");
		member2.setUserFavoriteArtist("윤딴딴2");
		member2.setUserFavoriteMusic("겨울을 걷는다2");
		userRepository.save(member2);
		Member result2 = userRepository.findByUserNo(member2.getUserNo());

		Fund fund2 = new Fund();
		fund2.setMember(result2);
		fund2.setFundTypeNo(FundTypeEnum.CONCERT);
		fund2.setFundTitle("밥먹고싶어");
		fund2.setStartDate(Date.valueOf("2024-05-03"));
		fund2.setEndDate(Date.valueOf("2024-05-04"));
		fund2.setPaymentDate(Date.valueOf("2024-05-03"));
		fund2.setFundStatus("Y");

		fundingAdminUserRepository.save(fund2);

		System.out.println("========= 3 =============");
///////////////////////////////////////////////////////////////////

		Member member3 = new Member();
		member3.setUserId("jjdkk");
		member3.setUserPwd("123458");
		member3.setUserName("규민씨");
		member3.setNickname("매우공격적인남자");
		member3.setPhone("010-5454-7878");
		member3.setAddress("가마산로3");
		member3.setUserProfileImg("ㅇㅇ3");
		member3.setUserContent("ㅇㅇ3");
		member3.setUserFavoriteArtist("윤딴딴3");
		member3.setUserFavoriteMusic("겨울을 걷는다3");
		userRepository.save(member3);
		Member result3 = userRepository.findByUserNo(member3.getUserNo());

		Fund fund3 = new Fund();
		fund3.setMember(result3);
		fund3.setFundTypeNo(FundTypeEnum.CONCERT);
		fund3.setFundTitle("배가고픕니다");
		fund3.setStartDate(Date.valueOf("2024-05-03"));
		fund3.setEndDate(Date.valueOf("2024-05-04"));
		fund3.setPaymentDate(Date.valueOf("2024-05-03"));
		fund3.setFundStatus("Y");

		fundingAdminUserRepository.save(fund3);
		///////////////////////////////////////////////////////////////////

		Member member4 = new Member();
		member4.setUserId("mmewii");
		member4.setUserPwd("123459");
		member4.setUserName("누구인가");
		member4.setNickname("그대는누구인가");
		member4.setPhone("010-8878-1545");
		member4.setAddress("가마산로4");
		member4.setUserProfileImg("ㅇㅇ4");
		member4.setUserContent("ㅇㅇ4");
		member4.setUserFavoriteArtist("윤딴딴4");
		member4.setUserFavoriteMusic("겨울을 걷는다4");
		userRepository.save(member4);
		Member result4 = userRepository.findByUserNo(member4.getUserNo());

		Fund fund4 = new Fund();
		fund4.setMember(result4);
		fund4.setFundTypeNo(FundTypeEnum.CONCERT);
		fund4.setFundTitle("누구인가~");
		fund4.setStartDate(Date.valueOf("2024-05-03"));
		fund4.setEndDate(Date.valueOf("2024-05-04"));
		fund4.setPaymentDate(Date.valueOf("2024-05-03"));
		fund4.setFundStatus("Y");

		fundingAdminUserRepository.save(fund4);

	}

}
