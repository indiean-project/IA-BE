package com.ia.indieAn.domain.artist.dto;

import com.ia.indieAn.entity.artist.Artist;
import lombok.Data;

@Data
public class LineupSearchDto {

    private int artistNo;
    private String artistName;


    public LineupSearchDto(Artist artist){
        this.artistNo = artist.getArtistNo();
        this.artistName = artist.getArtistName();
    }
}
