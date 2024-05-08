package com.ia.indieAn.admin.user.dto;


import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.entity.user.Member;
import lombok.Data;

import java.util.Date;

@Data
public class FundingUserDto {

    private int fundNo;
    private int userNo;
    private int fundTypeNo;
    private String fundTitle;
    private String fundStatus;

    public FundingUserDto(Fund fund){
        this.fundNo = fund.getFundNo();
        this.userNo = fund.getMember().getUserNo();
        this.fundTypeNo = Integer.parseInt(fund.getFundTypeNo().getCode());
        this.fundTitle = fund.getFundTitle();
        this.fundStatus = fund.getFundStatus();

    }


}
