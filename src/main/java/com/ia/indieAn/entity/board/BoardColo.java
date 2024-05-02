package com.ia.indieAn.entity.board;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;

@DynamicInsert
@Entity
@Table(name = "board_colo")
@Data
public class BoardColo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coloNo;

    @OneToOne
    @JoinColumn(name = "board_no")
    private Board board;

    @Column(nullable = false)
    private String colRightTitle;

    @Column(nullable = false)
    private String colLeftTitle;

}
