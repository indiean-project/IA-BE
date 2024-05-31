package com.ia.indieAn.domain.fund.dto;

import com.ia.indieAn.type.enumType.FundTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class FundListDto {

    private Integer fundNo;
    private Integer userNo;
    private String fundTitle;
    private Date startDate;
    private Date endDate;
    private String fundInfo;
    private String fundType;
    private String fundDescription;
    private Integer target;

    //펀딩액 현황
    private Integer revenue;
    private Integer rate;
    private String artistName;
    private String imgUrl;

//    public FundListDto(FundListByRevenueInterface fund) {
//        this.fundNo = fund.getFundNo();
//        this.userNo = fund.getUserNo();
//        this.fundTitle = fund.getFundTitle();
//        this.startDate = fund.getStartDate();
//        this.endDate = fund.getEndDate();
//        this.fundInfo = fund.getFundInfo();
//        this.fundType = String.valueOf(FundTypeEnum.find(fund.getFundTypeNo()));
//        this.fundDescription = fund.getFundDescription();
//        this.target = fund.getTarget();
//        this.revenue = fund.getRevenue();
//
//        // 선생님이 경악한 방식, 생성자 안에서 for문은 돌리지 말 것.
////        for (int i = 0; i < fund.getOrderLogList().size(); i++) {
////            this.revenue += fund.getOrderLogList().get(i).getTotalPrice();
////      }
//    }
    public static FundListDto convertToPage(FundListByRevenueInterface fi){
        return FundListDto.builder()
                .fundNo(fi.getFundNo())
                .userNo(fi.getUserNo())
                .fundTitle(fi.getFundTitle())
                .startDate(fi.getStartDate())
                .endDate(fi.getEndDate())
                .fundInfo(fi.getFundInfo())
                .fundType(String.valueOf(FundTypeEnum.find(fi.getFundTypeNo())))
                .fundDescription(fi.getFundDescription())
                .target(fi.getTarget())
                .revenue(fi.getRevenue())
                .rate(fi.getRate())
                .artistName(fi.getArtistName())
                .imgUrl(fi.getImgUrl())
                .build();
    }
}
