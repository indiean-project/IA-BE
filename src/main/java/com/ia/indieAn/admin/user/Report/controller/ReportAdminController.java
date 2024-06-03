package com.ia.indieAn.admin.user.Report.controller;


import com.ia.indieAn.admin.user.Question.dto.QuestionAdminDto;
import com.ia.indieAn.admin.user.Report.dto.ReportAdminDto;
import com.ia.indieAn.admin.user.Report.dto.ReportAdminSearchDto;
import com.ia.indieAn.admin.user.Report.service.ReportAdminService;
import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class ReportAdminController {

    private static final Logger log = LoggerFactory.getLogger(ReportAdminController.class);
    @Autowired
    ReportAdminService reportAdminService;

    @ResponseBody
    @RequestMapping("/report/reportList")
    public ResponseEntity<ResponseTemplate> reportList(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        ArrayList<ReportAdminDto> result = reportAdminService.selectReportList();
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/report/updateStatus")
    public ResponseEntity<ResponseTemplate> updateStatus(@RequestBody ReportAdminDto reportAdminDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        String solveYn = reportAdminDto.getSolveYn();
        int reportNo = reportAdminDto.getReportNo();

        reportAdminService.updateReportStatus(reportAdminDto.getReportNo(), reportAdminDto.getSolveYn());
        ArrayList<ReportAdminDto> result = reportAdminService.selectReportList();
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(result);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    
    @ResponseBody
    @RequestMapping("/report/searchList")
    public ResponseEntity<ResponseTemplate> searchList(@RequestBody ReportAdminSearchDto ReportAdminSearchDto){

      log.info("searchList ~~~~~{}", "테스트");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();



        ArrayList<ReportAdminDto> result = reportAdminService.searchReportList(ReportAdminSearchDto);
        response.setStatus(StatusEnum.SUCCESS);
        response.setData(result);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }



//    @ResponseBody
//    @RequestMapping("/report/selectBoardNo")
//    public ResponseEntity<ResponseTemplate> selectBoardNo(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        ResponseTemplate response = new ResponseTemplate();
//
//        ArrayList<ReportAdminDto> result = reportAdminService.selectReportList();
//        response.setStatus(StatusEnum.SUCCESS);
//        response.setData(result);
//
//        return new ResponseEntity<>(response, headers, HttpStatus.OK);
//
//    }

}
