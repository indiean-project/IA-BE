package com.ia.indieAn.admin.user.dto;


import com.ia.indieAn.entity.user.Question;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionAdminDto {

    private int questionNo;
    private int userNo;
    private String questionContent;
    private String ansYn;
    private Date questionDate;


    public QuestionAdminDto(Question question) {
        this.questionNo = question.getQuestionNo();
        this.userNo = question.getMember().getUserNo();
        this.questionContent = question.getQuestionContent();
        this.ansYn = question.getAnsYn();
        this.questionDate = question.getQuestionDate();
    }
}
