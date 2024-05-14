package com.ia.indieAn.domain.fund.dto;


import java.time.LocalDateTime;
import java.util.Date;

public interface FundListByRevenueInterface {

    Integer getFundNo();
    Integer getUserNo();
    String getFundTitle();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
    String getFundInfo();
    String getFundTypeNo();
    String getFundDescription();
    Integer getTarget();
    Integer getRevenue();

    Integer getRate();
}
