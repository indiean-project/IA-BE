package com.ia.indieAn.domain.board.dto;

import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.BoardColo;
import lombok.Data;

import java.sql.Date;

@Data
public class ColoBoardDto {
    private int boardNo;
    private String Nickname;
    private Date enrollDate;
    private Date updateDate;
    private String boardTitle;
    private String boardContent;
    private int viewCount;
    private int likeCount;
    private int replies;
    private String colLeftTitle;
    private String colRightTitle;

    public ColoBoardDto(Board board) {
        this.boardNo = board.getBoardNo();
        this.Nickname = board.getMember().getNickname();
        this.enrollDate = board.getEnrollDate();
        this.updateDate = board.getUpdateDate();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.viewCount = board.getViewCount();
        this.replies = board.getReplies().size();
        this.colLeftTitle = board.getBoardColo().getColLeftTitle();
        this.colRightTitle = board.getBoardColo().getColRightTitle();
    }
}
