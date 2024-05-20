package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.entity.user.Member;
import lombok.Data;

@Data
public class UserPageDto {
    private int userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String nickname;
    private String phone;
    private String address;
    private String userProfileImg;
    private String userContent;
    private String userFavoriteArtist;
    private String userFavoriteMusic;

    public UserPageDto(Member member) {
        this.userNo = member.getUserNo();
        this.userId = member.getUserId();
        this.userPwd = member.getUserPwd();
        this.userName = member.getUserName();
        this.nickname = member.getNickname();
        this.phone = member.getPhone();
        this.address = member.getAddress();
        this.userProfileImg = member.getUserProfileImg();
        this.userContent = member.getUserContent();
        this.userFavoriteArtist = member.getUserFavoriteArtist();
        this.userFavoriteMusic = member.getUserFavoriteMusic();

        System.out.println(this);
    }

}
