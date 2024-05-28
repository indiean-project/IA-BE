package com.ia.indieAn.domain.board.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.domain.board.repository.ReportRepository;
import com.ia.indieAn.entity.board.ContentReportLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Transactional(rollbackFor = CustomException.class)
    public void reportEnroll(ContentReportLog contentReportLog) {
        reportRepository.save(contentReportLog);
    }
}
