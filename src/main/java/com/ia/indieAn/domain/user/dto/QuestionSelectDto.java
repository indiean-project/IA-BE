package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.entity.user.Question;

public class QuestionSelectDto {
    private int questionNo;
    private String questionContent;
    private String questionDate;
    private String ansYn;
    private String ansDate;

    public QuestionSelectDto(Question question) {
        this.questionNo = question.getQuestionNo();
        this.questionContent = question.getQuestionContent();
        this.questionDate = String.valueOf(question.getQuestionDate());
        this.ansYn = question.getAnsYn();
        this.ansDate = String.valueOf(question.getAnsDate());
    }

}
