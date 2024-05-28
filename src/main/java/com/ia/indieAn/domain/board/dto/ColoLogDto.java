package com.ia.indieAn.domain.board.dto;

import com.ia.indieAn.entity.board.ColoLog;
import com.ia.indieAn.type.enumType.RlTypeEnum;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColoLogDto {
    private int coloNo;
    private int userNo;
    private String cancelYn;
    private RlTypeEnum vote;

    public ColoLogDto(ColoLog coloLog) {
        this.coloNo = coloLog.getColoLogNo();
        this.userNo = coloLog.getMember().getUserNo();
        this.cancelYn = coloLog.getCancelYn();
        this.vote = coloLog.getVote();
    }
}


