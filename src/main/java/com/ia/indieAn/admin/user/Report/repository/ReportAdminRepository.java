package com.ia.indieAn.admin.user.repository;


import com.ia.indieAn.entity.board.ContentReportLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReportAdminRepository extends JpaRepository<ContentReportLog, Integer> {


}
