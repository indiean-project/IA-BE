package com.ia.indieAn.domain.artist.dto;

import java.time.LocalDate;

public interface ArtistDtoProjection {

    Integer getArtistNo();
    LocalDate getDebutDate();
    Integer getUserNo();
    String getArtistName();
    String getMusicCategory();
    String getImgUrl();

}
