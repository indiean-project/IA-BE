package com.ia.indieAn;

import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.converter.FundTypeConverter;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import com.ia.indieAn.type.enumType.UserRoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IndieAnApplicationTests {

	@Autowired
	UserRepository userRepository;


	@Test
	void contextLoads() throws Exception{
		Member member = new Member();
		member.setUserId("comet2667");
		member.setUserPwd("123123");
		member.setUserName("박혜성");
		member.setNickname("옥암동불꽃낙지");
		member.setPhone("01077052667");
		member.setUserRole(UserRoleEnum.ARTIST);

		Member result = userRepository.save(member);
		System.out.println(result);


		System.out.println();

	}

}
