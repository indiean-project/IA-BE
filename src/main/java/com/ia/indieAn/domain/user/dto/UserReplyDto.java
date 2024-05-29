package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.type.enumType.ContentTypeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserReplyDto {
    private int replyNo;
    private int boardNo;
    private String replyContent;
    private String createDate;
    private String contentType;

    public static UserReplyDto userReplyHistory(UserReplyProjection rp) {
        return UserReplyDto.builder()
                .replyNo(rp.getReplyNo())
                .boardNo(rp.getBoardNo())
                .replyContent(rp.getReplyContent())
                .createDate(rp.getCreateDate())
                .contentType(getContentTypeValue(rp.getContentTypeNo()))
                .build();
    }

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
