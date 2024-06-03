package com.ia.indieAn.admin.user.Report.repository;


import com.ia.indieAn.entity.board.ContentReportLog;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.enumType.BrcTypeEnum;
import com.ia.indieAn.type.enumType.ReportTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;


public interface ReportAdminRepository extends JpaRepository<ContentReportLog, Integer> {

    Optional<ContentReportLog> findByReportNo(int reportNo);
    ArrayList<ContentReportLog> findByBrType(String brType);
    ArrayList<ContentReportLog> findByReportTypeNo(String reportTypeNo);
    ArrayList<ContentReportLog> findBySolveYn(String solveYn);
    ArrayList<ContentReportLog> findByMember(Member member);
}
