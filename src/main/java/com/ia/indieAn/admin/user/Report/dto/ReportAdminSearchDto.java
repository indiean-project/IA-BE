package com.ia.indieAn.admin.user.Report.dto;

import lombok.Data;

@Data
public class ReportAdminSearchDto {

    private String searchValue;
    private String sortValue;
    private String keyword;
    private int page;
}
