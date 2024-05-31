package com.ia.indieAn.domain.user.dto;

import com.ia.indieAn.type.enumType.ContentTypeEnum;

public interface UserReplyProjection {
    Integer getReplyNo();
    Integer getBoardNo();
    String getReplyContent();
    String getCreateDate();
    Integer getContentTypeNo();
}
