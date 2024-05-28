package com.ia.indieAn.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRewardOrderDto {
    private int rewardNo;
    private String rewardName;
    private int rewardPrice;
    private int rewardAmount;
    private int rewardTotalPrice;

    public static UserRewardOrderDto rewardOrderHistory(UserRewardOrderProjection roi) {
        return UserRewardOrderDto.builder()
                .rewardNo(roi.getRewardNo())
                .rewardName(roi.getRewardName())
                .rewardPrice(roi.getRewardPrice())
                .rewardAmount(roi.getRewardAmount())
                .rewardTotalPrice(roi.getRewardTotalPrice())
                .build();
    }
}
