package com.ia.indieAn.admin.user.Authority.dto;

import com.ia.indieAn.entity.artist.Artist;
import com.ia.indieAn.entity.user.Member;
import lombok.Data;

import java.util.Date;

@Data
public class AuthorityAdminDto {

    private int artistNo;
//    private int userNo;
    private Member member;
    private String artistName;
    private String musicCategory;
    private String artistInfo;
    private Date debutDate;
    private String artistRoleStatus;

    public AuthorityAdminDto(Artist artist) {
        this.artistNo = artist.getArtistNo();
//        this.userNo= artist.getMember().getUserNo();
        this.artistName = artist.getArtistName();
        this.musicCategory = artist.getMusicCategory();
        this.artistInfo = artist.getArtistInfo();
        this.debutDate = artist.getDebutDate();
        this.member = artist.getMember();


    }


}
