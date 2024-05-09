package com.ia.indieAn.domain.dto;

import com.ia.indieAn.entity.fund.Fund;
import lombok.Data;

import java.sql.Date;

@Data
public class FundListDto {

    private int fundNo;
    private int userNo;
    private String fundTitle;
    private Date startDate;
    private Date endDate;
    private String fundInfo;
    private String fundType;
    private String fundDescription;
    private int target;

    //펀딩액 현황
    private int revenue;

    public FundListDto(Fund fund) {
        this.fundNo = fund.getFundNo();
        this.userNo = fund.getMember().getUserNo();
        this.fundTitle = fund.getFundTitle();
        this.startDate = fund.getStartDate();
        this.endDate = fund.getEndDate();
        this.fundInfo = fund.getFundInfo();
        this.fundType = fund.getFundTypeNo().getValue();
        this.fundDescription = fund.getFundDescription();
        this.target = fund.getTarget();
        for (int i = 0; i < fund.getOrderLogList().size(); i++) {
            this.revenue += fund.getOrderLogList().get(i).getTotalPrice();
        }
    }
}
