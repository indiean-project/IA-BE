package com.ia.indieAn.entity.board;

import com.ia.indieAn.entity.user.Member;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;

@DynamicInsert
@Entity
@Table(name = "colo_log")
@Data
public class ColoLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coloLogNo;

    @ManyToOne
    @JoinColumn(name = "colo_no")
    private BoardColo boardColo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private Member member;

    @Column(nullable = false)
    private String vote; // R인지 L인지

    @Column(columnDefinition = "char(1) default 'N'")
    private String cancelYn;
}
