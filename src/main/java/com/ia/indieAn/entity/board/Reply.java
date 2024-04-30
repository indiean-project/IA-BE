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
@Table(name = "reply")
@Data
public class Reply {

    @Id
    private int replyNo;

    private int userNo;
    private int boardNo;
    private LocalDateTime createDate;

    @ColumnDefault("N")
    private String deleteYn;

    private String replyContent;
}
