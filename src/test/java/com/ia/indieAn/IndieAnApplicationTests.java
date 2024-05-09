package com.ia.indieAn;

import com.ia.indieAn.domain.board.repository.ContentLikeLogRepository;
import com.ia.indieAn.domain.board.repository.FreeBoardRepository;
import com.ia.indieAn.domain.board.repository.ReplyRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.ContentLikeLog;
import com.ia.indieAn.entity.board.Reply;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.converter.FundTypeConverter;
import com.ia.indieAn.type.enumType.BrTypeEnum;
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

	@Autowired
	ContentLikeLogRepository contentLikeLogRepository;

	@Autowired
	ReplyRepository replyRepository;

	@Autowired
	FreeBoardRepository freeBoardRepository;


	@Test
	void contextLoads() throws Exception{

//		for (int i = 0; i < 20; i++) {
//			Member member = new Member();
//			member.setUserId("comet2667"+i);
//			member.setUserPwd("123123"+i);
//			member.setUserName("박혜성"+i);
//			member.setNickname("옥암동불꽃낙지"+i);
//			member.setPhone("01077052667"+i);
//			member.setUserRole(UserRoleEnum.ARTIST);
//			userRepository.save(member);
//		}

//		for (int i = 0; i < 20; i++) {
//			ContentLikeLog test = new ContentLikeLog();
//			test.setMember(userRepository.findByUserNo(i+1));
//			test.setBrType(BrTypeEnum.BOARD);
//			test.setContentNo(22);
//			contentLikeLogRepository.save(test);
//		}

		for (int i = 0; i < 20; i++) {
			Reply test = new Reply();
			test.setMember(userRepository.findByUserNo(1));
			test.setBoard(freeBoardRepository.findByBoardNo(22));
			test.setDeleteYn("N");
			test.setReplyContent("이것은 샘플 댓글");
			replyRepository.save(test);
		}


	}

}
