package com.ia.indieAn.domain.concert.dto;

import com.ia.indieAn.entity.concert.ConcertLineup;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Data
public class ConcertEnrollDto {

    private String concertTitle;

    private String location;

    private Date startDate;

    private int concertPrice;

    private String runtime;

    private String ticketUrl;

    private Date endDate;

    private String concertInfo;

    private List<SearchLineupDto> concertLineupList = new ArrayList<>();
}
