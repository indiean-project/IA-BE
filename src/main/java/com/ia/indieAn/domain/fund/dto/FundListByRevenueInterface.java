package com.ia.indieAn.domain.fund.dto;


import java.time.LocalDateTime;
import java.util.Date;

public interface FundListByRevenueInterface {

    Integer getFundNo();
    Integer getUserNo();
    String getFundTitle();
    Date getStartDate();
    Date getEndDate();
    String getFundInfo();
    String getFundTypeNo();
    String getFundDescription();
    Long getTarget();
    Long getRevenue();

    Integer getRate();
    String getArtistName();
    String getImgUrl();
}
