package com.ia.indieAn.entity.fund;

import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.converter.FundTypeConverter;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@Entity
@Table(name = "fund")
@Data
public class Fund implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fundNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private Member member;

    @Convert(converter = FundTypeConverter.class)
    @Column(nullable = false)
    private FundTypeEnum fundTypeNo;

    @Column(nullable = false)
    private String fundTitle;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Temporal(TemporalType.DATE)
    private Date deleteDate;

    @Column(columnDefinition = "char(1) default 'N'")
    private String deleteYn;    // 펀딩 삭제여부

    @Column(columnDefinition = "char(1) default 'N'")
    private String endYn;       // 펀딩 마감여부

    @Column(columnDefinition = "char(1) default 'N'")
    private String compYn;      // 펀딩 성공여부

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Temporal(TemporalType.DATE)
    private Date paymentDate;   // 결제 예정일

    @Column(nullable = true)
    private String fundInfo;

    @Column(nullable = true)
    private String artistInfo;

    @Column(nullable = true)
    private String rewardInfo;

    @Column(nullable = true)
    private String budgetManage;

    @Column(nullable = true)
    private String schedule;

    @Column(nullable = false)
    private String fundStatus; //임시저장, 승인대기, 반려, 승인 Enum 작업 필요

    @OneToMany(mappedBy = "fund")
    private List<FundLog> fundLogList = new ArrayList<>();

    @OneToMany(mappedBy = "fund")
    private List<OrderLog> orderLogList = new ArrayList<>();

    @OneToMany(mappedBy = "fund")
    private List<Reward> rewardList = new ArrayList<>();
}
