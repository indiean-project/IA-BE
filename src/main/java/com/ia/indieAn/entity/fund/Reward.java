package com.ia.indieAn.entity.fund;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Entity
@Table(name = "reward")
@Data
public class Reward {

    @Id
    private int rewardNo;

    private int fundNo;
    private String rewardName;
    private int rewardPrice;
    private String rewardInfo;

    @ColumnDefault("'N'")
    private String deliveryYn;

    private String limitYn;
    private int limitAmount;

}
