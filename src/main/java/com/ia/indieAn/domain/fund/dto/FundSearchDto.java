package com.ia.indieAn.domain.fund.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class FundSearchDto {

    private String sortValue;
    private Sort.Direction sort;
    private int page;
    private String keyword;
    private String searchValue;

    public void setSort(String sort){
        this.sort = sort.equals("DESC") ?
                Sort.Direction.DESC
                : Sort.Direction.ASC;
    }
}
