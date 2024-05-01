package com.ia.indieAn.entity.fund;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@DynamicInsert
@Entity
@Table(name = "fund")
@Data
public class Fund {

    @Id
    private int fundNo;

    private int userNo;
    private int fundTypeNo;
    private String fundTitle;
    private LocalDateTime createDate;
    private LocalDateTime deleteDate;

    @ColumnDefault("'N'")
    private String deleteYn;
    @ColumnDefault("'N'")
    private String endYn;
    @ColumnDefault("'N'")
    private String compYn;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime paymentDate;
    private String fundInfo;
    private String artistInfo;
    private String rewardInfo;
    private String budgetManage;
    private String schedule;
    private String fundStatus; //임시저장인지 어쩌구


}
