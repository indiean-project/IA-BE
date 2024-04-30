package com.ia.indieAn.entity.fund;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@DynamicInsert
@Entity
@Table(name = "fund_log")
@Data
public class FundLog {

    @Id
    private int fundLogNo;

    private int userNo;
    private int fundNo;
    private int rewardNo;
    private LocalDateTime fundDate; //언제 펀딩했는지
    private int rewardAmount;   //선택한 리워드 수량

}
