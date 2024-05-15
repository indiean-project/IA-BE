package com.ia.indieAn.domain.fund.dto;

import com.ia.indieAn.entity.fund.Reward;
import lombok.Data;

@Data
public class RewardListDto {
    private int rewardNo;
    private int fundNo;
    private String rewardName;
    private int rewardPrice;
    private String rewardInfo;
    private String deliveryYn;
    private String limitYn;

    public RewardListDto(Reward reward){
        this.rewardNo = reward.getRewardNo();
        this.fundNo = reward.getFund().getFundNo();
        this.rewardName = reward.getRewardName();
        this.rewardPrice = reward.getRewardPrice();
        this.rewardInfo = reward.getRewardInfo();
        this.deliveryYn = reward.getDeliveryYn();
        this.limitYn = reward.getLimitYn();
    }
}
