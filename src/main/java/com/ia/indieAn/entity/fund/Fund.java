package com.ia.indieAn.entity.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.converter.FundTypeConverter;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "fund")
public class Fund implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fundNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private Member member;

    @Convert(converter = FundTypeConverter.class)
    @Column(nullable = false)
    private FundTypeEnum fundTypeNo;

    @Column(nullable = false)
    private String fundTitle;

    @Column(nullable = false)
    private String fundDescription;

    @Column(nullable = false)
    private int target;

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

    @Column(nullable = false)
    private String fundInfo;

    @Column(nullable = false)
    private String artistInfo;

    @Column(nullable = false)
    private String rewardInfo;

    @Column(nullable = false)
    private String budgetManage;

    @Column(nullable = false)
    private String schedule;

    @Column(nullable = false)
    private String fundStatus; //임시저장, 승인대기, 반려, 승인 Enum 작업 필요

    @JsonIgnoreProperties({"fund"})
    @OneToMany(mappedBy = "fund")
    private List<FundLog> fundLogList = new ArrayList<>();

    @JsonIgnoreProperties({"fund"})
    @OneToMany(mappedBy = "fund")
    private List<OrderLog> orderLogList = new ArrayList<>();

    @JsonIgnoreProperties({"fund"})
    @OneToMany(mappedBy = "fund")
    private List<Reward> rewardList = new ArrayList<>();

    @Override
    public String toString() {
        return "Fund{" +
                "fundNo=" + fundNo +
                ", userNo=" + member.getUserNo() +
                ", fundTypeNo=" + fundTypeNo +
                ", fundTitle='" + fundTitle + '\'' +
                ", fundDescription='" + fundDescription + '\'' +
                ", target=" + target +
                ", createDate=" + createDate +
                ", deleteDate=" + deleteDate +
                ", deleteYn='" + deleteYn + '\'' +
                ", endYn='" + endYn + '\'' +
                ", compYn='" + compYn + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", paymentDate=" + paymentDate +
                ", fundInfo='" + fundInfo + '\'' +
                ", artistInfo='" + artistInfo + '\'' +
                ", rewardInfo='" + rewardInfo + '\'' +
                ", budgetManage='" + budgetManage + '\'' +
                ", schedule='" + schedule + '\'' +
                ", fundStatus='" + fundStatus + '\'' +
                ", fundLogList=" + fundLogList.size() +
                ", orderLogList=" + orderLogList.size() +
                ", rewardList=" + rewardList.size() +
                '}';
    }
}
