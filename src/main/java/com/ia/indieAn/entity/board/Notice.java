package com.ia.indieAn.entity.board;

import com.ia.indieAn.entity.user.Member;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@DynamicInsert
@Entity
@Table(name = "notice")
@Data
public class Notice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noticeNo;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String noticeTitle;

    @Column(nullable = false)
    private String noticeContent;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date enrollDate;

    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @Column(columnDefinition = "char(1) default 'N'")
    private String deleteYn;

    @Column(columnDefinition = "integer default 0")
    private int viewCount;

}
