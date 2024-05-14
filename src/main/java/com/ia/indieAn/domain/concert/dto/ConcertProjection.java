package com.ia.indieAn.domain.concert.dto;

import java.time.LocalDate;

public interface ConcertProjection {

    Integer getConcertNo();

    String getConcertTitle();

    String getLocation();

    LocalDate getStartDate();

    LocalDate getEndDate();

    LocalDate getCreateDate();

    Integer getDay();

    String getDeleteYn();

}
