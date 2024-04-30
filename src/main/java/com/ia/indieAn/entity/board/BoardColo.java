package com.ia.indieAn.entity.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Entity
@Table(name = "board_colo")
@Data
public class BoardColo {

    @Id
    private int coloNo;

    private int boardNo;
    private String colRightTitle;
    private String colLeftTitle;

}
