package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.entity.user.Question;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionSelectDto {
    private int questionNo;
    private String questionContent;
    private String questionDate;
    private String ansYn;
    private String ansDate;
    private String ansContent;

    public static QuestionSelectDto questionHistory(QuestionSelectProjection qs) {
        return QuestionSelectDto.builder()
                .questionNo(qs.getQuestionNo())
                .questionContent(qs.getQuestionContent())
                .questionDate(qs.getQuestionDate())
                .ansYn(qs.getAnsYn())
                .ansDate(qs.getAnsDate())
                .ansContent(qs.getAnsContent())
                .build();
    }

}
