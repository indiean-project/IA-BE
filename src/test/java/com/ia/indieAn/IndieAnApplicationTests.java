package com.ia.indieAn;

import com.ia.indieAn.domain.concert.repository.ConcertRepository;
import com.ia.indieAn.domain.fund.repository.FundRepository;
import com.ia.indieAn.domain.fund.repository.OrderLogRepository;
import com.ia.indieAn.domain.fund.repository.RewardRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.concert.Concert;
import com.ia.indieAn.entity.concert.ConcertLineup;
import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.entity.fund.OrderLog;
import com.ia.indieAn.entity.fund.Reward;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import com.ia.indieAn.type.enumType.UserRoleEnum;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
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
			member.setUserId("comet2667@"+i);
			member.setUserPwd("1231@as23"+i);
			member.setUserName("박혜성"+i);
			member.setNickname("옥암동불꽃낙지"+i);
			member.setPhone("0107705266"+i);
			member.setUserRole(UserRoleEnum.ARTIST);
			userRepository.save(member);
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


	}

}
