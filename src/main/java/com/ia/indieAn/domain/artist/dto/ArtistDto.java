package com.ia.indieAn.domain.artist.dto;

import com.ia.indieAn.domain.concert.dto.ConcertDto;
import com.ia.indieAn.entity.artist.Artist;
import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArtistDto {

    private int artistNo;
    private Date debutDate;
    private int userNo;
    private String artistName;
    private String musicCategory;
    private String imgUrl;


    public ArtistDto(Artist artist){
        this.artistNo = artist.getArtistNo();
        this.debutDate = artist.getDebutDate();
        this.userNo = artist.getMember().getUserNo();
        this.artistName = artist.getArtistName();
        this.musicCategory = artist.getMusicCategory();

    }

    public static ArtistDto conventToArtistDto(ArtistDtoProjection ap){
        return builder()
                .artistNo(ap.getArtistNo())
                .debutDate(Date.valueOf(ap.getDebutDate()))
                .userNo(ap.getUserNo())
                .artistName(ap.getArtistName())
                .musicCategory(ap.getMusicCategory())
                .imgUrl(ap.getImgUrl())
                .build();
    }


}
