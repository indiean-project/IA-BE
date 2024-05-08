package com.ia.indieAn.domain.board.dto;

import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.Reply;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class FreeBoardDto {
    private int boardNo;
    private int userNo;
    private Date enrollDate;
    private Date updateDate;
    private String boardTitle;
    private String boardContent;
    private int viewCount;
    private List<Reply> replies;

    public FreeBoardDto(Board board) {
        this.boardNo = board.getBoardNo();
        this.userNo = board.getMember().getUserNo();
        this.enrollDate = board.getEnrollDate();
        this.updateDate = board.getUpdateDate();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.viewCount = board.getViewCount();
        this.replies = board.getReplies();
    }
}
