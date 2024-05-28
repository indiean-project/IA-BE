package com.ia.indieAn.domain.board.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SideBarListDto {
    private Object likeListDto;
    private Object viewListDto;
}
