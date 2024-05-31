package com.ia.indieAn.admin.user.Report.dto;


import com.ia.indieAn.entity.board.ContentReportLog;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ReportAdminDto {

    private int reportNo;
    private String nickName;
    private String reportTypeNo;
    private String solveYn;
    private Date reportDate;
    private int contentNo;
    private String brType;

    public ReportAdminDto(ContentReportLog report) {
        this.reportNo = report.getReportNo();
        this.nickName = report.getMember().getNickname();
        this.reportTypeNo= report.getReportTypeNo().getValue();
        this.solveYn = report.getSolveYn();
        this.reportDate = report.getReportDate();
        this.contentNo = report.getContentNo();
        this.brType= report.getBrType().getValue();
    }



}
