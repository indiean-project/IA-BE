package com.ia.indieAn.admin.user.Fund.dto;


import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.type.enumType.FundStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FundingAdminUserDto {

    private int fundNo;
    private String artistName;
    private String fundTitle;
    private Date createDate;
    private Date startDate;
    private Date endDate;
    private Date responseDate;
    private FundStatusEnum fundStatus;


    public FundingAdminUserDto(Fund fund){
        this.fundNo = fund.getFundNo();
        this.artistName = fund.getMember().getUserName(); // 이것 제대로 못받아오는거 같다.
        this.fundTitle = fund.getFundTitle();
        this.createDate = fund.getCreateDate();
        this.startDate = fund.getStartDate();
        this.endDate = fund.getEndDate();
        this.responseDate = fund.getResponseDate();
        this.fundStatus=fund.getFundStatus();

    }


}
