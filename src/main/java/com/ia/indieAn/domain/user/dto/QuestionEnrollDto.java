package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.entity.user.Question;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Data
@NoArgsConstructor
@Setter
public class QuestionEnrollDto {
    private int questionNo;
    private int userNo;
    private String questionDate;
    private String questionContent;

    public QuestionEnrollDto(Question question) {
        this.questionNo = question.getQuestionNo();
        this.userNo = question.getMember().getUserNo();
        this.questionDate = String.valueOf(question.getQuestionDate());
        this.questionContent = question.getQuestionContent();
    }
}
