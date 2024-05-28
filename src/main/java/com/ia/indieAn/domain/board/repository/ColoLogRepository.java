package com.ia.indieAn.domain.board.repository;

import com.ia.indieAn.domain.board.dto.ColoLogDto;
import com.ia.indieAn.entity.board.ColoLog;
import com.ia.indieAn.type.enumType.RlTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ColoLogRepository extends JpaRepository<ColoLog, Integer> {
    int countByBoardColo_ColoNoAndVoteAndCancelYn(int coloNo, RlTypeEnum vote, String cancelYn);
    ColoLog findByBoardColo_ColoNoAndMember_UserNo(int coloNo, int userNo);
    ArrayList<ColoLog> findByMember_UserNoAndCancelYn(int userNo, String cancelYn);
}
