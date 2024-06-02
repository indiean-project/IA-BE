package com.ia.indieAn.domain.fund.dto;

import com.ia.indieAn.entity.fund.Reward;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RewardListDto {
    private int rewardNo;
    private int fundNo;
    private String rewardName;
    private long rewardPrice;
    private String rewardInfo;
    private String deliveryYn;
    private String limitYn;
    private int limitAmount;

    public RewardListDto(Reward reward){
        this.rewardNo = reward.getRewardNo();
        this.fundNo = reward.getFund().getFundNo();
        this.rewardName = reward.getRewardName();
        this.rewardPrice = reward.getRewardPrice();
        this.rewardInfo = reward.getRewardInfo();
        this.deliveryYn = reward.getDeliveryYn();
        this.limitYn = reward.getLimitYn();
        this.limitAmount = reward.getLimitAmount();
    }
}
