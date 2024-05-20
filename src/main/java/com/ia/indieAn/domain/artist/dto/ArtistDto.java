package com.ia.indieAn.domain.artist.dto;

import com.ia.indieAn.entity.artist.Artist;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
public class ArtistDto {

    private int artistNo;
    private Date debutDate;
    private int userNo;
    private String artistName;
    private String musicCategory;
    private String imgUrl;


    public ArtistDto(Artist artist, String imgUrl){
        this.artistNo = artist.getArtistNo();
        this.debutDate = artist.getDebutDate();
        this.userNo = artist.getMember().getUserNo();
        this.artistName = artist.getArtistName();
        this.musicCategory = artist.getMusicCategory();
        this.imgUrl = imgUrl;
    }

}
