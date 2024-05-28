package com.ia.indieAn.domain.board.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.board.service.ReportService;
import com.ia.indieAn.entity.board.ContentReportLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/report")
@CrossOrigin
public class ReportController {

    @Autowired
    ReportService reportService;

    @RequestMapping("/enroll")
    public ResponseEntity<ResponseTemplate> reportEnroll(@RequestBody ContentReportLog contentReportLog) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        reportService.reportEnroll(contentReportLog);

        response.setStatus(StatusEnum.SUCCESS);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
