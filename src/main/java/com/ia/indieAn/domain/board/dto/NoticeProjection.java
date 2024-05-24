package com.ia.indieAn.domain.board.dto;

public interface NoticeProjection {
    Integer getNoticeNo();
    String getNickName();
    String getNoticeTitle();
    String getNoticeContent();
    Integer getViewCount();
    String getEnrollDate();
    String getUpdateDate();
}
