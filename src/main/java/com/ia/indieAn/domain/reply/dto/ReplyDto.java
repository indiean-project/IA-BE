package com.ia.indieAn.domain.reply.dto;

import com.ia.indieAn.entity.board.Reply;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
    private int boardNo;
    private Date createDate;
    private int replyNo;
    private String nickName;
    private String deleteYn;
    private String replyContent;
    private int userNo;

    public ReplyDto(Reply reply) {
        this.boardNo = reply.getBoard().getBoardNo();
        this.createDate = reply.getCreateDate();
        this.replyNo = reply.getReplyNo();
        this.nickName = reply.getMember().getNickname();
        this.deleteYn = reply.getDeleteYn();
        this.replyContent = reply.getReplyContent();
        this.userNo = reply.getMember().getUserNo();
    }

    @Override
    public String toString() {
        return "boardNo = " + boardNo
                + "createDate = " + createDate
                + "replyNo = " + replyNo
                + "nickName = " + nickName
                + "deleteYn = " + deleteYn
                + "replyContent = " + replyContent
                + "userNo = " + userNo;
    }
}
