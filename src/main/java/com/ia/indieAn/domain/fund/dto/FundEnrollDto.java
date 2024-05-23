package com.ia.indieAn.domain.fund.dto;

import com.ia.indieAn.type.enumType.FundTypeEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class FundEnrollDto {

    private int userNo;
    private String fundTitle;
    private String fundDescription;
    private String fundInfo;
    private FundTypeEnum category;
    private String budgetInfo;
    private String artistInfo;
    private String scheduleInfo;
    private Date startDate;
    private Date endDate;
    private Date paymentDate;
    private int target;
    private List<RewardListDto> reward = new ArrayList<>();
}
