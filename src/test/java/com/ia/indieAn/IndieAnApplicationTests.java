package com.ia.indieAn;

import com.ia.indieAn.domain.concert.repository.ConcertRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.concert.Concert;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.converter.FundTypeConverter;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import com.ia.indieAn.type.enumType.UserRoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootTest
class IndieAnApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ConcertRepository concertRepository;


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

		for(int i = 0; i < 20; i++){
			Concert concert = new Concert();
			concert.setConcertNo(i);
			concert.setConcertTitle("타이틀"+i);
			concert.setLocation("주소123");
			concert.setStartDate(Date.valueOf(LocalDate.parse("2024-05-14")));
			concert.setEndDate(Date.valueOf(LocalDate.parse("2024-05-20")));
			concert.setConcertInfo("이런 저런이야기");
			concert.setDeleteYn("N");
			concertRepository.save(concert);
		}



	}

}
