package com.ia.indieAn.domain.board.repository;

import com.ia.indieAn.entity.board.ContentLikeLog;
import com.ia.indieAn.type.enumType.BrTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentLikeLogRepository extends JpaRepository<ContentLikeLog, Integer> {
    ContentLikeLog findByMember_UserNoAndContentNoAndBrType(int userNo, int contentNo, BrTypeEnum brType);
}
