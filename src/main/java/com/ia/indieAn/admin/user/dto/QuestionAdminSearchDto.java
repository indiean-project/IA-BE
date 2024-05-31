package com.ia.indieAn.admin.user.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class QuestionAdminSearchDto {
    private String searchValue;
    private String sortValue;
    private int page;
    private String keyword;
}
