package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.type.enumType.ReportTypeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportSelectDto {
    private int reportNo;
    private String reportDate;
    private String nickname;
    private String reportType;
    private String solveYn;

    public static ReportSelectDto reportHistory(ReportSelectProjection rsp) {
        return ReportSelectDto.builder()
                .reportNo(rsp.getReportNo())
                .reportDate(rsp.getReportDate())
                .nickname(rsp.getNickname())
                .reportType(getReportTypeValue(rsp.getReportTypeNo()))
                .solveYn(rsp.getSolveYn())
                .build();
    }

    private static String getReportTypeValue(Integer reportTypeNo) {
        if (reportTypeNo == null) {
            return "";
        }
        for (ReportTypeEnum type : ReportTypeEnum.values()) {
            if (type.getCode().equals(String.valueOf(reportTypeNo))) {
                return type.getValue();
            }
        }
        return "";
    }
}

