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
@Table(name = "content_report_log")
@Data
public class ContentReportLog {

    @Id
    private int reportNo;

    private int userNo;
    private int reportTypeNo;

    @ColumnDefault("'N'")
    private String solveYn;

    private LocalDateTime reportDate;
    private int contentNo;
    private String brType;
}
