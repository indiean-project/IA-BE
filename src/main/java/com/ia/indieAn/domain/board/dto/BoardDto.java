package com.ia.indieAn.domain.board.dto;

import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.type.enumType.UserRoleEnum;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
    private int boardNo;
    private String nickname;
    private String enrollDate;
    private String updateDate;
    private String boardTitle;
    private String boardContent;
    private String userRole;
    private int viewCount;
    private int likeCount;
    private int replies;
    private String imgUrl;

//    public BoardDto(Board board) {
//        this.boardNo = board.getBoardNo();
//        this.nickname = board.getMember().getNickname();
//        this.enrollDate = board.getEnrollDate();
//        this.updateDate = board.getUpdateDate();
//        this.boardTitle = board.getBoardTitle();
//        this.boardContent = board.getBoardContent();
//        this.viewCount = board.getViewCount();
//        this.replies = board.getReplies().size();
//        this.imgUrl = getImgUrl();
//        this.userRole = board.getMember().getUserRole().getCode();
//        this.likeCount = getLikeCount();
//    }

    @Override
    public String toString() {
        return "boardNo = " + boardNo
                + "nickname = " + nickname
                + "enrollDate = " + enrollDate
                + "updateDate = " + updateDate
                + "boardTitle = " + boardTitle
                + "boardContent = " + boardContent
                + "viewCount = " + viewCount
                + "replies = " + replies
                + "imgUrl = " + imgUrl;
    }

    public static BoardDto convertBoardDto(BoardProjection bp) {
        return builder()
                .boardNo(bp.getBoardNo())
                .boardTitle(bp.getBoardTitle())
                .boardContent(bp.getBoardContent())
                .replies(bp.getReplies())
                .nickname(bp.getNickname())
                .userRole(bp.getUserRole())
                .enrollDate(bp.getEnrollDate())
                .updateDate(bp.getUpdateDate())
                .viewCount(bp.getViewCount())
                .likeCount(bp.getLikeCount())
                .build();

    }
}
