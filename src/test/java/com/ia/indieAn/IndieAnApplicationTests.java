package com.ia.indieAn;

import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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
		member.setAddress("광진구 화양동");
		member.setCreateDate(LocalDateTime.now());
		member.setRoleNo(2);

		Member result = userRepository.save(member);

		System.out.println(result);
	}

}
