package com.ia.indieAn.domain.imgurl.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.domain.imgurl.dto.ImgUrlDto;
import com.ia.indieAn.domain.imgurl.service.ImgUrlService;
import com.ia.indieAn.entity.board.ImgUrl;
import com.ia.indieAn.type.enumType.FabcTypeEnum;
import com.ia.indieAn.type.enumType.KcTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api/imgurl")
@CrossOrigin
@Slf4j
public class ImgUrlController {

    @Autowired
    ImgUrlService imgUrlService;

    @RequestMapping("/enroll")
    public ResponseEntity<ResponseTemplate> imgEnroll(@RequestBody ImgUrlDto imgUrlDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        for(int i = 0; i < imgUrlDto.getImgUrlList().length; i++) {
            ImgUrl imgUrl = new ImgUrl();
            imgUrl.setImgUrl(imgUrlDto.getImgUrlList()[i]);
            imgUrl.setContentNo(imgUrlDto.getContentNo());
            imgUrl.setFabcType(imgUrlDto.getFabcTypeEnum());
            imgUrl.setKcType(KcTypeEnum.CONTENT);

            imgUrlService.imgEnroll(imgUrl);
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
