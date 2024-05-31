package com.ia.indieAn.domain.concert.dto;
import com.ia.indieAn.entity.concert.ConcertReply;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConcertReplyDto {

    private int concertReplyNo;

    private String nickName;

    private int concertNo;

    private Date createDate;

    private String replyContent;

    private int userNo;

    private String deleteYn;

    public ConcertReplyDto(ConcertReply concertReply){
        this.concertReplyNo = concertReply.getConcertReplyNo();
        this.nickName = concertReply.getMember().getNickname();
        this.concertNo = concertReply.getConcert().getConcertNo();
        this.createDate = concertReply.getCreateDate();
        this.replyContent = concertReply.getReplyContent();;
        this.userNo = concertReply.getMember().getUserNo();
        this.deleteYn = concertReply.getDeleteYn();
    }

}
