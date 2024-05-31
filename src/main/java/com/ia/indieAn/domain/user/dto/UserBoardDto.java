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
    private String updateDate;
    private String nickname;
    private String boardTitle;
    private String boardContent;
    private String userRole;
    private int viewCount;
    private int likeCount;
    private String contentType;
    private int replies;

    public static UserBoardDto userBoardHistory(UserBoardProjection bp) {
        return UserBoardDto.builder()
                .boardNo(bp.getBoardNo())
                .boardTitle(bp.getBoardTitle())
                .replies(bp.getReplies())
                .userRole(bp.getUserRole())
                .nickname(bp.getNickname())
//                .contentTypeNo
                .updateDate(bp.getUpdateDate())
                .viewCount(bp.getViewCount())
                .likeCount(bp.getLikeCount())
                .contentType(getContentTypeValue(bp.getContentTypeNo()))
                .build();
    }   // Projection 형성은 interface에서 가져오기에 from을 붙임

    private static String getContentTypeValue(Integer contentTypeNo) {
        if (contentTypeNo == null) {
            return "";
        }
        for (ContentTypeEnum type : ContentTypeEnum.values()) {
            if (type.getCode().equals(String.valueOf(contentTypeNo))) {
                return type.getValue();
            }
        }
        return "";
    }
}
