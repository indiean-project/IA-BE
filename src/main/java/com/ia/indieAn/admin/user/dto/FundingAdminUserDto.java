package com.ia.indieAn.admin.user.dto;


import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.type.enumType.FundStatusEnum;
import lombok.Data;

@Data
public class FundingAdminUserDto {

    private int fundNo;
    private int userNo;
    private int fundTypeNo;
    private String fundTitle;
    private FundStatusEnum fundStatus;

    public FundingAdminUserDto(Fund fund){
        this.fundNo = fund.getFundNo();
        this.userNo = fund.getMember().getUserNo();
        this.fundTypeNo = Integer.parseInt(fund.getFundTypeNo().getCode());
        this.fundTitle = fund.getFundTitle();
        this.fundStatus = fund.getFundStatus();
    }


}
