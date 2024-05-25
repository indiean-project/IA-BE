package com.ia.indieAn.domain.board.repository;

import com.ia.indieAn.entity.board.ContentReportLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ContentReportLog, Integer> {
}
