package com.ia.indieAn.entity.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ia.indieAn.domain.fund.dto.RewardListDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@DynamicInsert
@Entity
@Table(name = "reward")
public class Reward implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rewardNo;

    @ManyToOne
    @JoinColumn(name = "fund_no")
    private Fund fund;

    @Column(nullable = false)
    private String rewardName;

    @Column(nullable = false)
    private int rewardPrice;

    @Column(nullable = false)
    private String rewardInfo;

    @Column(columnDefinition = "char(1) default 'N'")
    private String deliveryYn;

    @Column(nullable = false)
    private String limitYn;

    private int limitAmount;

    @JsonIgnoreProperties({"reward"})
    @OneToMany(mappedBy = "reward")
    private List<FundLog> fundLogList = new ArrayList<>();

    @Override
    public String toString() {
        return "Reward{" +
                "rewardNo=" + rewardNo +
                ", fundNo=" + fund.getFundNo() +
                ", rewardName='" + rewardName + '\'' +
                ", rewardPrice=" + rewardPrice +
                ", rewardInfo='" + rewardInfo + '\'' +
                ", deliveryYn='" + deliveryYn + '\'' +
                ", limitYn='" + limitYn + '\'' +
                ", limitAmount=" + limitAmount +
                ", fundLogList=" + fundLogList.size() +
                '}';
    }

    public static Reward convertFromRewardDto(RewardListDto rewardListDto, Fund fund){
        return Reward.builder()
                .fund(fund)
                .rewardName(rewardListDto.getRewardName())
                .rewardPrice(rewardListDto.getRewardPrice())
                .rewardInfo(rewardListDto.getRewardInfo())
                .deliveryYn(rewardListDto.getDeliveryYn())
                .limitYn(rewardListDto.getLimitYn())
                .limitAmount(rewardListDto.getLimitAmount())
                .build();
    }
}
