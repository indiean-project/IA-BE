package com.ia.indieAn.admin.user.dto;


import com.ia.indieAn.entity.user.Question;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionAdminDto {

    private int questionNo;
    private int userNo;
    private String userId;
    private String userName;
    private String questionContent;
    private String ansYn;
    private Date questionDate;
    private Date answerDate;
    private String ansContent;


    public QuestionAdminDto(Question question) {
        this.questionNo = question.getQuestionNo();
        this.userNo = question.getMember().getUserNo();
        this.questionContent = question.getQuestionContent();
        this.ansYn = question.getAnsYn();
        this.questionDate = question.getQuestionDate();
        this.answerDate = question.getAnsDate();
        this.userId = question.getMember().getUserId();
        this.userName = question.getMember().getUserName();
        this.questionNo = question.getQuestionNo();
        this.ansContent = question.getAnsContent();

    }
}
