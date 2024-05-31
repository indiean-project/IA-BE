package com.ia.indieAn.admin.user.Question.dto;


import com.ia.indieAn.entity.user.Question;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class QuestionAdminDto {

    private int questionNo;
    private int userNo;
    private String userId;
    private String userName;
    private String questionContent;
    private String ansYn;
    private Date questionDate;
    private String answerDate;
    private String ansContent;


    public QuestionAdminDto(Question question) {
        this.questionNo = question.getQuestionNo();
        this.userNo = question.getMember().getUserNo();
        this.questionContent = question.getQuestionContent();
        this.ansYn = question.getAnsYn();
        this.questionDate = question.getQuestionDate();
        this.answerDate = String.valueOf(question.getAnsDate());
        this.userId = question.getMember().getUserId();
        this.userName = question.getMember().getUserName();
        this.questionNo = question.getQuestionNo();
        this.ansContent = question.getAnsContent();

    }
}
