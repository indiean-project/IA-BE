package com.ia.indieAn.domain.fund.dto;

import lombok.Data;

@Data
public class FundSearchDto {

    private String sortValue;
    private String sort;
    private int page;
    private String keyword;
    private String searchValue;
}
