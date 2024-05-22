package com.ia.indieAn.domain.board.dto;

import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.BoardColo;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColoBoardDto {
    private int boardNo;
    private int coloNo;
    private int userNo;
    private String nickname;
    private String userRole;
    private String enrollDate;
    private String updateDate;
    private String boardTitle;
    private String boardContent;
    private int viewCount;
    private int likeCount;
    private int replies;
    private String colLeftTitle;
    private String colRightTitle;
    private int colLeftCount;
    private int colRightCount;

    public ColoBoardDto(Board board) {
        this.boardNo = board.getBoardNo();
        this.coloNo = board.getBoardColo().getColoNo();
        this.userNo = board.getMember().getUserNo();
        this.nickname = board.getMember().getNickname();
        this.userRole = board.getMember().getUserRole().getCode();
        this.enrollDate = String.valueOf(board.getEnrollDate());
        this.updateDate = String.valueOf(board.getUpdateDate());
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.viewCount = board.getViewCount();
        this.likeCount = getLikeCount();
        this.replies = board.getReplies().size();
        this.colLeftTitle = board.getBoardColo().getColLeftTitle();
        this.colRightTitle = board.getBoardColo().getColRightTitle();
        this.colLeftCount = board.getBoardColo().getColoLogList().size();
        this.colRightCount = board.getBoardColo().getColoLogList().size();
    }

    @Override
    public String toString() {
        return "boardNo = " + boardNo
                + "coloNo = " + coloNo
                + "userNo = " + userNo
                + "nickname = " + nickname
                + "userRole" + userRole
                + "enrollDate = " + enrollDate
                + "updateDate = " + updateDate
                + "boardTitle = " + boardTitle
                + "boardContent = " + boardContent
                + "viewCount = " + viewCount
                + "likeCount = " + likeCount
                + "replies = " + replies
                + "colLeftTitle" + colLeftTitle
                + "colRightTitle" + colRightTitle
                + "colLeftCount" + colLeftCount
                + "colRightCount" + colRightCount;
    }

    public static ColoBoardDto convertColoBoardDto(BoardProjection bp) {
        return builder()
                .boardNo(bp.getBoardNo())
                .coloNo(bp.getColoNo())
                .userNo(bp.getUserNo())
                .nickname(bp.getNickname())
                .userRole(bp.getUserRole())
                .enrollDate(bp.getEnrollDate())
                .updateDate(bp.getUpdateDate())
                .boardTitle(bp.getBoardTitle())
                .boardContent(bp.getBoardContent())
                .viewCount(bp.getViewCount())
                .likeCount(bp.getLikeCount())
                .replies(bp.getReplies())
                .colLeftTitle(bp.getColLeftTitle())
                .colRightTitle(bp.getColRightTitle())
                .colLeftCount(bp.getColLeftCount())
                .colRightCount(bp.getColRightCount())
                .build();

    }
}
