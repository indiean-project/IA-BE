package com.ia.indieAn.domain.board.dto;

public interface BoardProjection {
    Integer getBoardNo();
    String getColoNo();
    String getBoardTitle();
    String getBoardContent();
    Integer getReplies();
    Integer getUserNo();
    String getNickname();
    String getUserRole();
    String getEnrollDate();
    String getUpdateDate();
    Integer getViewCount();
    Integer getLikeCount();
    String getImgUrl();
    String getColLeftTitle();
    String getColRightTitle();
    Integer getColLeftCount();
    Integer getColRightCount();
}
