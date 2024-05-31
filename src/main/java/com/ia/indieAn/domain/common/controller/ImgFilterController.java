package com.ia.indieAn.domain.common.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/api/imgfilter")
@CrossOrigin
@RequiredArgsConstructor
public class ImgFilterController {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @RequestMapping("/tempImg")
    public ResponseEntity<ResponseTemplate> tempImg(@RequestParam(value="image") MultipartFile image) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        String orgName = image.getOriginalFilename();
        String currTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int randNum = (int)(Math.random() * 90000 + 10000);
        String ext = orgName.substring(orgName.lastIndexOf("."));
        String chgName = currTime + randNum + ext;

        //aws s3 저장 로직
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getSize());
        metadata.setContentType(image.getContentType());

        amazonS3.putObject(bucket, "tempImg/"+chgName, image.getInputStream(), metadata);

        response.setStatus(StatusEnum.SUCCESS);
        response.setData(amazonS3.getUrl(bucket, "tempImg/"+chgName));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/imgDelete")
    public ResponseEntity<ResponseTemplate> imgDelete(@RequestBody String[] imgList) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        int sliceName = "https://indiebucket.s3.ap-northeast-2.amazonaws.com".length() + 1;

        for (int i = 0; i < imgList.length; i++) {
            amazonS3.deleteObject(bucket, imgList[i].substring(sliceName));
        }

        response.setStatus(StatusEnum.SUCCESS);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/imgMove")
    public ResponseEntity<ResponseTemplate> imgMove(@RequestBody String[] imgList) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        int sliceName = "https://indiebucket.s3.ap-northeast-2.amazonaws.com".length() + 1;
        int sliceName2 = "https://indiebucket.s3.ap-northeast-2.amazonaws.com/tempImg".length() + 1;

        ArrayList list = new ArrayList();

        for (int i = 0; i < imgList.length; i++) {
            amazonS3.copyObject(bucket, imgList[i].substring(sliceName), bucket, "img/"+imgList[i].substring(sliceName2));
            amazonS3.deleteObject(bucket, imgList[i].substring(sliceName));
            list.add(amazonS3.getUrl(bucket, "img/"+imgList[i].substring(sliceName2)));
        }

        response.setStatus(StatusEnum.SUCCESS);
        response.setData(list);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
