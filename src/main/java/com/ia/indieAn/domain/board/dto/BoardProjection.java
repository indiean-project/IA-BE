package com.ia.indieAn.domain.board.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BoardProjection {
    Integer getBoardNo();
    String getBoardTitle();
    String getBoardContent();
    Integer getReplies();
    String getNickname();
    String getUserRole();
    String getEnrollDate();
    String getUpdateDate();
    Integer getViewCount();
    Integer getLikeCount();
}
