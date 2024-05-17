package com.ia.indieAn.admin.user.dto;


import com.ia.indieAn.entity.fund.Fund;
import lombok.Data;

@Data
public class FundingAdminSearchOptionDto {

    private String type;        // 제목 / 펀딩타입 / 처리상태
    private String keyword;
}
