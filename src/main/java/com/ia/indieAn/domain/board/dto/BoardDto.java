package com.ia.indieAn.domain.board.dto;

import com.ia.indieAn.entity.board.Board;
import lombok.Data;

import java.sql.Date;

@Data
public class BoardDto {
    private int boardNo;
    private String Nickname;
    private Date enrollDate;
    private Date updateDate;
    private String boardTitle;
    private String boardContent;
    private int viewCount;
    private int likeCount;
    private int replies;
    private String imgUrl;

    public BoardDto(Board board) {
        this.boardNo = board.getBoardNo();
        this.Nickname = board.getMember().getNickname();
        this.enrollDate = board.getEnrollDate();
        this.updateDate = board.getUpdateDate();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.viewCount = board.getViewCount();
        this.replies = board.getReplies().size();
        this.imgUrl = getImgUrl();
    }
}
