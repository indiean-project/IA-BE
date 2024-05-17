package com.ia.indieAn.admin.user.dto;


import com.ia.indieAn.entity.fund.Fund;
import lombok.Data;

@Data
public class FundingAdminUserDto {

    private int fundNo;         // sequence-> pk값
    private int userNo;         // 유저정보 PK값
    private String fundTypeNo;  // 펀딩 타입
    private String fundTitle;   // 펀딩 요청글 제목
    private String fundStatus;  // 펀딩 처리상태 Y/N

    public FundingAdminUserDto(Fund fund){
        this.fundNo = fund.getFundNo();
        this.userNo = fund.getMember().getUserNo();
        this.fundTypeNo = fund.getFundTypeNo().getValue();
        this.fundTitle = fund.getFundTitle();
        this.fundStatus = fund.getFundStatus();
    }


}
