package com.ia.indieAn.domain.concert.dto;

import com.ia.indieAn.entity.concert.Concert;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConcertDto {

    private int concertNo;

    private String concertTitle;

    private String location;

    private Date startDate;

    private Date endDate;

    private Date createDate;

    private String deleteYn;

    private String titleUrl;


    public ConcertDto(Concert concert) {
        this.concertNo = concert.getConcertNo();
        this.location = concert.getLocation();
        this.startDate = concert.getStartDate();
        this.endDate = concert.getEndDate();
        this.concertTitle = concert.getConcertTitle();
        this.deleteYn = concert.getDeleteYn();
    }


    @Override
    public String toString() {
        return "concertNo = " + concertNo
                + "concertTitle =" + concertTitle
                + "location =" + location
                + "startDate =" + startDate
                + "endDate = " + endDate
                + "deleteYn = " + deleteYn;
    }

    public static ConcertDto convertToPage(ConcertProjection cp) {

        return builder()
                .concertNo(cp.getConcertNo())
                .concertTitle(cp.getConcertTitle())
                .location(cp.getLocation())
                .startDate(Date.valueOf(cp.getStartDate()))
                .endDate(Date.valueOf(cp.getEndDate()))
                .deleteYn(cp.getDeleteYn())
                .titleUrl(cp.getImgUrl())
                .build();
    }
}
