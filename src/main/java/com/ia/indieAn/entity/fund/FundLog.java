package com.ia.indieAn.entity.fund;

import com.ia.indieAn.entity.user.Member;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@DynamicInsert
@Entity
@Table(name = "fund_log")
@Data
public class FundLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fundLogNo;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "fund_no", nullable = false)
    private Fund fund;

    @ManyToOne
    @JoinColumn(name = "reward_no", nullable = false)
    private Reward reward;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date fundDate; //언제 펀딩했는지

    @Column(nullable = false)
    private int rewardAmount;   //선택한 리워드 수량

}
