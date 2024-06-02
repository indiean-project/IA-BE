package com.ia.indieAn.domain.fund.dto;

import lombok.Data;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderReserveDto {

    private int userNo;
    private int fundNo;
    private long totalPrice;
    private List<RewardReserveListDto> reward = new ArrayList<>();
    private String receiptId;
    private Date paymentDate;
}
