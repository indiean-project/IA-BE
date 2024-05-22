package com.ia.indieAn.domain.user.dto;

public interface UserBoardProjection {
    Integer getBoardNo();
    String getBoardTitle();
    Integer getReplies();
    String getNickname();
    String getUserRole();
    String getUpdateDate();
    Integer getViewCount();
    Integer getLikeCount();
}
