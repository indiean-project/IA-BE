package com.ia.indieAn.domain.concert.dto;

import lombok.Data;

@Data
public class ConcertReplyDto {

    private int concertReplyNo;

    private int userNo;

    private int concertNo;

    private String replyContent;


}
