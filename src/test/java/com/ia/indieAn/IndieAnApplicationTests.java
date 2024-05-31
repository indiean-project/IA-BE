package com.ia.indieAn;

import com.ia.indieAn.admin.user.Question.repository.QuestionAdminRepository;
import com.ia.indieAn.admin.user.Report.repository.ReportAdminRepository;
import com.ia.indieAn.domain.artist.repository.ArtistRepository;
import com.ia.indieAn.domain.board.repository.*;

import com.ia.indieAn.domain.board.repository.BoardRepository;
import com.ia.indieAn.domain.board.repository.ColoBoardRepository;
import com.ia.indieAn.domain.concert.repository.ConcertRepository;
import com.ia.indieAn.domain.fund.repository.FundRepository;
import com.ia.indieAn.domain.fund.repository.OrderLogRepository;
import com.ia.indieAn.domain.fund.repository.RewardRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;

import com.ia.indieAn.entity.board.ContentReportLog;

import com.ia.indieAn.entity.user.Question;
import com.ia.indieAn.type.enumType.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

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
	ReportAdminRepository reportAdminRepository;

	@Autowired
	ArtistRepository artistRepository;


	@Autowired
	NoticeRepository noticeRepository;

    @Autowired
    private QuestionAdminRepository questionAdminRepository;


	@Test
	void contextLoads() throws Exception{
		// 문의사항 test 데이터
		for (int i = 0; i< 15; i++){
			Question question = new Question();
			question.setQuestionNo(i);
			question.setMember(userRepository.findByUserNo(i+1));
			question.setAnsYn("N");
			question.setQuestionContent("테스트 데이터입니다"+i);
			question.setQuestionDate(Date.valueOf("2024-05-"+(i+1)));
			question.setAnsDate(null);
			question.setAnsContent("");

			questionAdminRepository.save(question);

		}

		// 신고사항 test 데이터
		for (int i =0; i<20; i++){
			ContentReportLog reportLog = new ContentReportLog();
			reportLog.setReportNo(i);
			reportLog.setMember(userRepository.findByUserNo(i+1));
			reportLog.setReportTypeNo(ReportTypeEnum.MYUNGYE);
			reportLog.setSolveYn("N");
			reportLog.setReportDate(Date.valueOf("2024-05-"+(i+1)));
			reportLog.setContentNo(i);
			if (i < 7) {
				reportLog.setBrType(BrcTypeEnum.BOARD);
			} else if (i >= 7 &&  i<10) {
				reportLog.setBrType(BrcTypeEnum.REPLY);
			} else if (i >= 10 && i <20) {
				reportLog.setBrType(BrcTypeEnum.CONCERTREPLY);
			}

			reportAdminRepository.save(reportLog);

		}
	}
}