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
@Table(name = "reply")
@Data
public class Reply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_no")
    private Board board;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(columnDefinition = "char(1) default 'N'")
    private String deleteYn;

    @Column(nullable = false)
    private String replyContent;
}
