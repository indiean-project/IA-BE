package com.ia.indieAn.entity.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ia.indieAn.domain.fund.dto.FundEnrollDto;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.converter.FundStatusConverter;
import com.ia.indieAn.type.converter.FundTypeConverter;
import com.ia.indieAn.type.enumType.FundStatusEnum;
import com.ia.indieAn.type.enumType.FundTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@Builder
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
    @Size(max = 4000)
    private String fundInfo;

    @Column(nullable = false)
    @Size(max = 4000)
    private String artistInfo;

    private String rewardInfo;

    @Column(nullable = false)
    private String budgetManage;

    @Column(nullable = false)
    private String schedule;

    @Convert(converter = FundStatusConverter.class)
    @Column(nullable = false)
    private FundStatusEnum fundStatus; //승인대기(N), 반려(R), 승인(A) Enum 작업 완료

    private Date responseDate;

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

    public static Fund convertFormFundEnrollDto(FundEnrollDto fundEnrollDto, Member member){
        return Fund.builder()
                .fundTitle(fundEnrollDto.getFundTitle())
                .member(member)
                .fundDescription(fundEnrollDto.getFundDescription())
                .fundTypeNo(fundEnrollDto.getCategory())
                .target(fundEnrollDto.getTarget())
                .startDate(fundEnrollDto.getStartDate())
                .endDate(fundEnrollDto.getEndDate())
                .fundInfo(fundEnrollDto.getFundInfo())
                .artistInfo(fundEnrollDto.getArtistInfo())
                .budgetManage(fundEnrollDto.getBudgetInfo())
                .schedule(fundEnrollDto.getScheduleInfo())
                .paymentDate(fundEnrollDto.getPaymentDate())
                .fundStatus(fundEnrollDto.getFundStatus())
                .build();
    }
}
