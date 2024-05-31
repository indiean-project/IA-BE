package com.ia.indieAn.admin.user.Report.repository;


import com.ia.indieAn.entity.board.ContentReportLog;
import com.ia.indieAn.type.enumType.BrcTypeEnum;
import com.ia.indieAn.type.enumType.ReportTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.AbstractDocument;
import java.util.ArrayList;
import java.util.Optional;


public interface ReportAdminRepository extends JpaRepository<ContentReportLog, Integer> {

    Optional<ContentReportLog> findByReportNo(int reportNo);
    ArrayList<ContentReportLog> findByBrType(BrcTypeEnum brType);
    ArrayList<ContentReportLog> findByReportTypeNo(ReportTypeEnum reportTypeNo);
    ArrayList<ContentReportLog> findBySolveYn(String solveYn);
}
