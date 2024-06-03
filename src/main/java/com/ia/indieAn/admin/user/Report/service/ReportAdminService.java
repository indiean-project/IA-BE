package com.ia.indieAn.admin.user.Report.service;


import com.ia.indieAn.admin.user.Fund.dto.FundingAdminUserDto;
import com.ia.indieAn.admin.user.Report.dto.ReportAdminDto;
import com.ia.indieAn.admin.user.Report.dto.ReportAdminSearchDto;
import com.ia.indieAn.admin.user.Report.repository.ReportAdminRepository;
import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.board.ContentReportLog;
import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.enumType.BrcTypeEnum;
import com.ia.indieAn.type.enumType.ReportTypeEnum;
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

    @Autowired
    private UserRepository userRepository;

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

    public ArrayList<ReportAdminDto> searchReportList(ReportAdminSearchDto searchDto){
        ArrayList<ReportAdminDto> resultList = new ArrayList<>();

        ArrayList<ContentReportLog> reportList = new ArrayList<>();
        if(searchDto.getSearchValue().equals("brType")){
            if (searchDto.getKeyword().equals("게시글")){
                reportList = reportAdminRepository.findByBrType(BrcTypeEnum.BOARD.getCode());
            }else if (searchDto.getKeyword().equals("댓글")){
                reportList = reportAdminRepository.findByBrType(BrcTypeEnum.REPLY.getCode());
            } else if (searchDto.getKeyword().equals("콘서트댓글")) {
                reportList = reportAdminRepository.findByBrType(BrcTypeEnum.CONCERTREPLY.getCode());
            }
            for(ContentReportLog contentReportLog : reportList){
                resultList.add(new ReportAdminDto(contentReportLog));
            }
        } else if (searchDto.getSearchValue().equals("reportTypeNo")){
             if(searchDto.getKeyword().equals("허위사실유포")) {
                   reportList = reportAdminRepository.findByReportTypeNo(ReportTypeEnum.HEOWI.getCode());
               }else if(searchDto.getKeyword().equals("명예훼손")){
                   reportList = reportAdminRepository.findByReportTypeNo(ReportTypeEnum.MYUNGYE.getCode());
               }else if(searchDto.getKeyword().equals("욕설")){
                   reportList = reportAdminRepository.findByReportTypeNo(ReportTypeEnum.YOK.getCode());
               }else if(searchDto.getKeyword().equals("광고")){
                   reportList = reportAdminRepository.findByReportTypeNo(ReportTypeEnum.AD.getCode());
               }else if(searchDto.getKeyword().equals("기타")){
                   reportList = reportAdminRepository.findByReportTypeNo(ReportTypeEnum.GUITAR.getCode());
               }
            for(ContentReportLog contentReportLog : reportList){
                resultList.add(new ReportAdminDto(contentReportLog));
            }

        }else if(searchDto.getSearchValue().equals("userName")){
           Member member = userRepository.findByUserName(searchDto.getKeyword());
            if (member != null){
                reportList = reportAdminRepository.findByMember(member);
                for(ContentReportLog contentReportLog : reportList){
                    resultList.add(new ReportAdminDto(contentReportLog));
                }
            }
        }

        return resultList;
    }

}
