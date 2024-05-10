package com.ia.indieAn.domain.board.dto;

import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.Reply;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class FreeBoardDto {
    private int boardNo;
    private String Nickname;
    private Date enrollDate;
    private Date updateDate;
    private String boardTitle;
    private String boardContent;
    private int viewCount;
    private int likeCount;
    private int replies;

    public FreeBoardDto(Board board) {
        this.boardNo = board.getBoardNo();
        this.Nickname = board.getMember().getNickname();
        this.enrollDate = board.getEnrollDate();
        this.updateDate = board.getUpdateDate();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.viewCount = board.getViewCount();
        this.replies = board.getReplies().size();
    }
}
