package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.entity.board.ContentReportLog;
import com.ia.indieAn.type.enumType.ReportTypeEnum;

public class ReportSelectDto {
    private int reportNo;
    private String reportDate;
    private String nickname;
    private ReportTypeEnum reportTypeNo;
    private String solveYn;

    public ReportSelectDto(ContentReportLog crl) {
        this.reportNo = crl.getReportNo();
        this.reportDate = String.valueOf(crl.getReportDate());
        this.nickname = crl.getMember().getNickname();
        this.reportTypeNo = crl.getReportTypeNo();
        this.solveYn = crl.getSolveYn();
    }
}
