package com.ia.indieAn.domain.fund.dto;

import lombok.Data;

@Data
public class FundSearchDto {

    private String sortValue;
    private String sort;
    private int page;
    private String keyword;
    private String searchValue;
    private String artistKeyword;
    private String titleKeyword;
    private String contentKeyword;
    private String allKeyword;
}
