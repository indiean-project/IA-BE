package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.entity.user.Member;
import lombok.Data;

@Data
public class LoginUserDto {

    private int userNo;
    private String userId;
    private String userName;
    private String nickname;
    private String phone;
    private String address;

    public LoginUserDto(Member member){
        this.userNo = member.getUserNo();
        this.userId = member.getUserId();
        this.userName = member.getUserName();
        this.nickname = member.getNickname();
        this.phone = member.getPhone();
        this.address = member.getAddress();
    }
}
