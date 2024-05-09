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




	}

}
