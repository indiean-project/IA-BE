package com.ia.indieAn.admin.user.dto;


import com.ia.indieAn.entity.fund.Fund;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.Date;

@Data
public class FundingAdminSearchDto {
    private String searchValue;
    private String sortValue;
    private int page;
    private Sort.Direction ad;
    private String keyword;

    public void setAd(String sort){
        this.ad = sort.equals("DESC") ?
                Sort.Direction.DESC
                : Sort.Direction.ASC;
    }


}



