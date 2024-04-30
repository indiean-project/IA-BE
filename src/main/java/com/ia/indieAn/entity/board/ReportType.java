package com.ia.indieAn.entity.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "report_type")
@Data
public class ReportType {

    @Id
    private int reportTypeNo;

    private String reportTypeName;
}
