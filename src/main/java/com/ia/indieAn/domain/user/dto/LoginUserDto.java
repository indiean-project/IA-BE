package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.entity.user.Member;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LoginUserDto {

    private int userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String nickname;
    private String phone;
    private String address;
    private String deleteYn;
    private String reportStatus;
    private String userProfileImg;
    private String userContent;
    private String userFavoriteArtist;
    private String userFavoriteMusic;

    public LoginUserDto(Member member){
        this.userNo = member.getUserNo();
        this.userId = member.getUserId();
        this.userPwd = member.getUserPwd();
        this.userName = member.getUserName();
        this.nickname = member.getNickname();
        this.phone = member.getPhone();
        this.address = member.getAddress();
        this.deleteYn = member.getDeleteYn();
        this.reportStatus = member.getReportStatus();
        this.userProfileImg = member.getUserProfileImg();
        this.userContent = member.getUserContent();
        this.userFavoriteArtist = member.getUserFavoriteArtist();
        this.userFavoriteMusic = member.getUserFavoriteMusic();
    }

}
