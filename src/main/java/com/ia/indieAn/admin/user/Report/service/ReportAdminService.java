package com.ia.indieAn.admin.user.Report.service;


import com.ia.indieAn.admin.user.Report.dto.ReportAdminDto;
import com.ia.indieAn.admin.user.Report.dto.ReportAdminSearchDto;
import com.ia.indieAn.admin.user.Report.repository.ReportAdminRepository;
import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
import com.ia.indieAn.entity.board.ContentReportLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
public class ReportAdminService {

    @Autowired
    ReportAdminRepository reportAdminRepository;

    public ArrayList<ReportAdminDto> selectReportList(){
        ArrayList<ContentReportLog> reportArrayList = (ArrayList<ContentReportLog>) reportAdminRepository.findAll(Sort.by(Sort.Direction.ASC, "reportNo"));
        ArrayList<ReportAdminDto> reportAdminDtoArrayList = new ArrayList<>(); // []
        for(int i=0; i<reportArrayList.size(); i++){
            reportAdminDtoArrayList .add(new ReportAdminDto(reportArrayList.get(i)));
        }
        return reportAdminDtoArrayList;
    }

    @Transactional(rollbackFor = CustomException.class)
    public void updateReportStatus(int reportNo, String solveYn){
     ContentReportLog reportLog = reportAdminRepository.findByReportNo(reportNo)
             .orElseThrow(() -> new CustomException(ErrorCode.FUND_NOT_FOUND));

        reportLog.setSolveYn("Y");
        reportAdminRepository.save(reportLog);
    }

//    public ArrayList<ReportAdminDto> searchReportList(ReportAdminSearchDto searchDto){
//        ArrayList<ReportAdminDto> resultList = new ArrayList<>();
//
//        ArrayList<ContentReportLog> reportList = new ArrayList<>();
//        if(searchDto.getSearchValue().equals("brType")){
//            ContentReportLog contentReportLog = reportAdminRepository.findByReportNo(Integer.parseInt(searchDto.getKeyword()))
//                    . orElseThrow( () -> new CustomException(ErrorCode.FUND_NOT_FOUND));
//        } else if (searchDto.getSearchValue().equals("reportTypeNo")){
//
//        }
//
//    }

}
