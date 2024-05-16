package com.ia.indieAn.domain.board.repository;

import com.ia.indieAn.entity.board.ColoLog;
import com.ia.indieAn.type.enumType.RlTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColoLogRepository extends JpaRepository<ColoLog, Integer> {
    int countByBoardColo_ColoNoAndVoteAndCancelYn(int coloNo, RlTypeEnum vote, String cancelYn);
}
