package com.ia.indieAn.domain.fund.dto;


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
    Integer getTarget();
    Integer getRevenue();
}
