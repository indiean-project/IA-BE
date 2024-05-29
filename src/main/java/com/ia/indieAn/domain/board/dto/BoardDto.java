package com.ia.indieAn.domain.board.dto;

import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
    private int boardNo;
    private int userNo;
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
    private int contentTypeNo;

    public BoardDto(Board board) {
        this.boardNo = board.getBoardNo();
        this.userNo = board.getMember().getUserNo();
        this.nickname = board.getMember().getNickname();
        this.enrollDate = String.valueOf(board.getEnrollDate());
        this.updateDate = String.valueOf(board.getUpdateDate());
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.userRole = board.getMember().getUserRole().getCode();
        this.viewCount = board.getViewCount();
        this.likeCount = getLikeCount();
        this.replies = board.getReplies().size();
        this.imgUrl = getImgUrl();
        this.contentTypeNo = Integer.parseInt(board.getContentTypeNo().getCode());
    }

    @Override
    public String toString() {
        return "boardNo = " + boardNo
                + "userNo = " + userNo
                + "nickname = " + nickname
                + "enrollDate = " + enrollDate
                + "updateDate = " + updateDate
                + "boardTitle = " + boardTitle
                + "boardContent = " + boardContent
                + "userRole = " + userRole
                + "viewCount = " + viewCount
                + "replies = " + replies
                + "imgUrl = " + imgUrl
                + "contentTypeNo = " + contentTypeNo;
    }

    public static BoardDto convertBoardDto(BoardProjection bp) {
        return builder()
                .boardNo(bp.getBoardNo())
                .boardTitle(bp.getBoardTitle())
                .boardContent(bp.getBoardContent())
                .replies(bp.getReplies())
                .userNo(bp.getUserNo())
                .nickname(bp.getNickname())
                .userRole(bp.getUserRole())
                .enrollDate(bp.getEnrollDate())
                .updateDate(bp.getUpdateDate())
                .viewCount(bp.getViewCount())
                .likeCount(bp.getLikeCount())
                .imgUrl(bp.getImgUrl())
                .build();

    }
}
