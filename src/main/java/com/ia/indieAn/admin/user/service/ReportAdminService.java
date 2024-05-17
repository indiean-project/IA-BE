package com.ia.indieAn.admin.user.service;


import com.ia.indieAn.admin.user.dto.ReportAdminDto;
import com.ia.indieAn.admin.user.repository.ReportAdminRepository;
import com.ia.indieAn.entity.board.ContentReportLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
public class ReportAdminService {

    @Autowired
    ReportAdminRepository reportAdminRepository;
    public ArrayList<ReportAdminDto> selectReportList(){
        ArrayList<ContentReportLog> reportArrayList = (ArrayList<ContentReportLog>) reportAdminRepository.findAll();
        ArrayList<ReportAdminDto> reportAdminDtoArrayList = new ArrayList<>(); // []
        for(int i=0; i<reportArrayList.size(); i++){
            reportAdminDtoArrayList .add(new ReportAdminDto(reportArrayList.get(i)));
        }
        return reportAdminDtoArrayList;
    }
}
