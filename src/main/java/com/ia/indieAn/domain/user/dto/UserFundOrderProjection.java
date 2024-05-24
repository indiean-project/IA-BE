package com.ia.indieAn.domain.user.dto;

import java.time.LocalDateTime;

public interface UserFundOrderProjection {
    Integer getFundNo();
    String getFundTitle();
    Integer getTotalPrice();
    String getOrderDate();
    String getPaymentDate();
}
