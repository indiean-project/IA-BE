package com.ia.indieAn;

import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.converter.FundTypeConverter;
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
		member.setUserId("zest@naver.com");
		member.setUserPwd("1q2w3e4r!@");
		member.setUserName("박혜성");
		member.setNickname("대세는제스트");
		member.setPhone("01012349698");
		member.setUserRole(UserRoleEnum.ARTIST);

		Member result = userRepository.save(member);
		System.out.println(result);



		System.out.println();

	}

}
