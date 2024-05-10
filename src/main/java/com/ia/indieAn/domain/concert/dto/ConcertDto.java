package com.ia.indieAn.domain.concert.dto;

import com.ia.indieAn.entity.concert.Concert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConcertDto {

    private int concertNo;

    private String concertTitle;

    private String location;

    private Date startDate;

    private Date endDate;

    private Date createDate;

    public ConcertDto(Concert concert){
        this.concertNo = concert.getConcertNo();
        this.location = concert.getLocation();
        this.startDate = concert.getStartDate();
        this.endDate = concert.getCreateDate();
        this.concertTitle = concert.getConcertTitle();
    }

    @Override
    public String toString() {
        return "concertNo = " + concertNo
                + "concertTitle =" + concertTitle
                + "location =" + location
                + "startDate =" + startDate
                + "endDate = " + endDate;
    }
}
