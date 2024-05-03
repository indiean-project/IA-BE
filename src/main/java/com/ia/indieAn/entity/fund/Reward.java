package com.ia.indieAn.entity.fund;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@Entity
@Table(name = "reward")
@Data
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

    @OneToMany(mappedBy = "reward")
    private List<FundLog> fundLogList = new ArrayList<>();
}
