package com.ia.indieAn.domain.fund.dto;

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

    public FundListDto(Fund fund, int revenue) {
        this.fundNo = fund.getFundNo();
        this.userNo = fund.getMember().getUserNo();
        this.fundTitle = fund.getFundTitle();
        this.startDate = fund.getStartDate();
        this.endDate = fund.getEndDate();
        this.fundInfo = fund.getFundInfo();
        this.fundType = fund.getFundTypeNo().getValue();
        this.fundDescription = fund.getFundDescription();
        this.target = fund.getTarget();
        this.revenue = revenue;

        // 선생님이 경악한 방식, 생성자 안에서 for문은 돌리지 말 것.
//        for (int i = 0; i < fund.getOrderLogList().size(); i++) {
//            this.revenue += fund.getOrderLogList().get(i).getTotalPrice();
//        }
    }
}
