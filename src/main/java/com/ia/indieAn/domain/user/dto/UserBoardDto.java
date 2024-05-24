package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.domain.board.dto.BoardDto;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBoardDto {
    private int userNo;
    private int boardNo;
    private String nickname;
    private String updateDate;
    private String boardTitle;
    private String boardContent;
    private String userRole;
    private int viewCount;
    private int likeCount;
    private int contentTypeNo;
    private int replies;

    public static UserBoardDto fromProjection(UserBoardProjection bp) {
        return UserBoardDto.builder()
                .boardNo(bp.getBoardNo())
                .boardTitle(bp.getBoardTitle())
                .replies(bp.getReplies())
                .nickname(bp.getNickname())
                .userRole(bp.getUserRole())
//                .contentTypeNo
                .updateDate(bp.getUpdateDate())
                .viewCount(bp.getViewCount())
                .likeCount(bp.getLikeCount())
                .build();
    }   // Projection 형성은 interface에서 가져오기에 from을 붙임

    public BoardDto toBoardDto() {
        return BoardDto.builder()
                .boardNo(this.boardNo)
                .nickname(this.nickname)
                .updateDate(this.updateDate)
                .boardTitle(this.boardTitle)
                .boardContent(this.boardContent)
                .userRole(this.userRole)
                .viewCount(this.viewCount)
                .likeCount(this.likeCount)
                .replies(this.replies)
                .build();
    }   // 실제로 가져온 객체를 전송할때는 to객체로 build해서 보냄.
}
