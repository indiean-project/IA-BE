package com.ia.indieAn.entity.concert;


import com.ia.indieAn.entity.user.Member;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = "concert_reply")
@Data
@DynamicInsert
public class ConcertReply {
    //ConcertReply Entity 생성 erd 수정 필요

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int concertReplyNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "concert_no")
    private Concert concert;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(columnDefinition = "char(1) default 'N'")
    private String deleteYn;

    @Column(nullable = false)
    private String replyContent;

    @Override
    public String toString() {
        return "Reply{" +
                "replyNo=" + concertReplyNo +
                ", userNo=" + member.getUserNo() +
                ", concertNo=" + concert.getConcertNo() +
                ", createDate=" + createDate +
                ", deleteYn='" + deleteYn + '\'' +
                ", replyContent='" + replyContent + '\'' +
                '}';
    }


}
