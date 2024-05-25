package com.ia.indieAn.domain.artist.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HomeArtistDto {
    private int artistNo;
    private String artistName;
    private String imgUrl;

    public static HomeArtistDto convertToProjection(HomeArtistProjection hp){
        return HomeArtistDto.builder()
                .artistNo(hp.getArtistNo())
                .artistName(hp.getArtistName())
                .imgUrl(hp.getImgUrl())
                .build();
    }
}
