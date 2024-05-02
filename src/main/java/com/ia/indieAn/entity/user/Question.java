package com.ia.indieAn.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;

@DynamicInsert
@Entity
@Table(name = "question")
@Data
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionNo; //getter, setter 메소드 camelCase 때문에 qNo -> questionNo로 바꿨습니다.

    @ManyToOne
    @JoinColumn(name = "user_no")
    private Member member;

    @Column(columnDefinition = "char(1) default 'N'")
    private String ansYn;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date questionDate;

    @Temporal(TemporalType.DATE)
    private Date ansDate;

    @Column(nullable = false)
    private String questionContent;

    private String ansContent;

}
