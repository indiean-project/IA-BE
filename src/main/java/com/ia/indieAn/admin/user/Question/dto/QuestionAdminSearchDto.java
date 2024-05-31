package com.ia.indieAn.admin.user.Question.dto;

import lombok.Data;

@Data
public class QuestionAdminSearchDto {
    private String searchValue;
    private String sortValue;
    private int page;
    private String keyword;
}
