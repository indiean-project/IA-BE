package com.ia.indieAn.domain.concert.dto;

import com.ia.indieAn.entity.concert.ConcertLineup;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Builder
public class ConcertDetailDto {

    private int concertNo;

    private String concertTitle;

    private String location;

    private Date startDate;

    private Date endDate;

    private String runtime;

    private String ticketUrl;

    private int concertPrice;

    private String concertInfo;

    private List<ConcertLineup> concertLineupList;

}
