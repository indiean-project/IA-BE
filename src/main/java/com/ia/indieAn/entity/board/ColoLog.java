package com.ia.indieAn.entity.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Entity
@Table(name = "colo_log")
@Data
public class ColoLog {

    @Id
    private int coloLogNo;

    private int coloNo;
    private int userNo;
    private String vote; // R인지 L인지

    @ColumnDefault("'N'")
    private String cancelYn;
}
