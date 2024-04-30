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
@Table(name = "notice")
@Data
public class Notice {

    @Id
    private int noticeNo;

    private int userNo;
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime enrollDate;
    private LocalDateTime updateDate;

    @ColumnDefault("N")
    private String deleteYn;

    private int viewCount;

}
