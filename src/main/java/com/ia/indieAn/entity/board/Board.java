package com.ia.indieAn.entity.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@DynamicInsert
@Entity
@Table(name = "board")
@Data
public class Board {

    @Id
    private int boardNo;

    private int userNo;
    private int contentTypeNo;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime enrollDate;
    private LocalDateTime updateDate;

    @ColumnDefault("N")
    private String deleteYn;

    private int viewCount;
    private int artistNo;

}
