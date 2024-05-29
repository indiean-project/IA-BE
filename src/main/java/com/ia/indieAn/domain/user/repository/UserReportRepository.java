package com.ia.indieAn.domain.user.repository;

import com.ia.indieAn.entity.board.ContentReportLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserReportRepository extends JpaRepository<ContentReportLog, Integer> {


    Optional<ContentReportLog> findByMember_UserNo(int userNo);
}

