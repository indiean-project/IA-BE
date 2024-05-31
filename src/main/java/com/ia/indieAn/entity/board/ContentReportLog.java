package com.ia.indieAn.entity.board;

import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.converter.BrTypeConverter;
import com.ia.indieAn.type.converter.ReportTypeConverter;
import com.ia.indieAn.type.enumType.BrcTypeEnum;
import com.ia.indieAn.type.enumType.ReportTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "content_report_log")
public class ContentReportLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private Member member;

    @Convert(converter = ReportTypeConverter.class)
    @Column(nullable = false)
    private ReportTypeEnum reportTypeNo;

    @Column(columnDefinition = "char(1) default 'N'")
    private String solveYn;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date reportDate;

    @Column(nullable = false)
    private int contentNo;
    /*
     * board_no, reply_no에 해당 되지만 2개 테이블의 기본키를 외래키로
     * 지정할 수 없기 때문에 물리적으론 외래키 지정하지 않음
     */

    @Convert(converter = BrTypeConverter.class)
    @Column(nullable = false)
    private BrcTypeEnum brType; //게시글(B)인지 댓글(R)인지 구분

    @Override
    public String toString() {
        return "ContentReportLog{" +
                "reportNo=" + reportNo +
                ", userName=" + member.getUserNo() +
                ", reportTypeNo=" + reportTypeNo +
                ", solveYn='" + solveYn + '\'' +
                ", reportDate=" + reportDate +
                ", contentNo=" + contentNo +
                ", brType=" + brType +
                '}';
    }
}
