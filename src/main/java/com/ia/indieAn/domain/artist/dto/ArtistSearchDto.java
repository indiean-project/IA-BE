package com.ia.indieAn.domain.artist.dto;


import lombok.Data;

@Data
public class ArtistSearchDto {

    private String keyword;
    private String sort;
    private int size;

}
