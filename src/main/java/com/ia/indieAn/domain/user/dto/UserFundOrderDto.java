package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.domain.fund.dto.FundDetailDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFundOrderDto {
    private int fundNo;
    private String fundTitle;
    private int totalPrice;
    private String orderDate;
    private String paymentDate;

    public static UserFundOrderDto fundOrderHistory(UserFundOrderProjection foi) {
        return UserFundOrderDto.builder()
                .fundNo(foi.getFundNo())
                .fundTitle(foi.getFundTitle())
                .totalPrice(foi.getTotalPrice())
                .orderDate(foi.getOrderDate())
                .paymentDate(foi.getPaymentDate())
                .build();
    }
}
