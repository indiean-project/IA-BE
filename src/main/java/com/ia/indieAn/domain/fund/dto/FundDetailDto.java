package com.ia.indieAn.domain.fund.dto;

import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class FundDetailDto {

    private int fundNo;
    private int userNo;
    private String fundType;
    private String fundTitle;
    private int target;
    private Date startDate;
    private Date endDate;
    private Date paymentDate;
    private String fundInfo;
    private String artistInfo;
    private String rewardInfo;
    private String budgetManage;
    private String schedule;
    private int revenue;
    private int people;
    private List<RewardListDto> rewardList = new ArrayList<>();
    private String[] imgUrlList;
    private int artistNo;

    public FundDetailDto(Fund fund , List<RewardListDto> rewardListDtos, int revenue, String[] imgUrlList, int artistNo){
        this.fundNo = fund.getFundNo();
        this.userNo = fund.getMember().getUserNo();
        this.fundType = String.valueOf(FundTypeEnum.find(fund.getFundTypeNo().getCode()));
        this.fundTitle = fund.getFundTitle();
        this.target = fund.getTarget();
        this.startDate = (Date) fund.getStartDate();
        this.endDate = (Date) fund.getEndDate();
        this.paymentDate = (Date) fund.getPaymentDate();
        this.fundInfo = fund.getFundInfo();
        this.artistInfo = fund.getArtistInfo();
        this.rewardInfo = fund.getRewardInfo();
        this.budgetManage = fund.getBudgetManage();
        this.schedule = fund.getSchedule();
        this.revenue = revenue;
        this.people = fund.getOrderLogList().size();
        this.rewardList = rewardListDtos;
        this.imgUrlList = imgUrlList;
        this.artistNo = artistNo;
    }
}
