package com.ia.indieAn.domain.board.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.board.dto.SideBarListDto;
import com.ia.indieAn.domain.board.service.BoardSideBarService;
import com.ia.indieAn.entity.board.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@Slf4j
@RestController
@RequestMapping("/api/board/side")
@CrossOrigin
public class BoardSideBarController {

    @Autowired
    BoardSideBarService boardSideBarService;

    @RequestMapping("/list")
    public ResponseEntity<ResponseTemplate> sideBarList(@RequestBody String category) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        String contentType = category.replaceAll("\"", "");

        SideBarListDto listDto = boardSideBarService.sideBarList(contentType);

        response.setStatus(StatusEnum.SUCCESS);
        response.setData(listDto);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
