package com.ia.indieAn.domain.user.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.board.dto.BoardDto;
import com.ia.indieAn.domain.fund.dto.FundListDto;
import com.ia.indieAn.domain.user.dto.*;
import com.ia.indieAn.domain.user.service.UserService;
import com.ia.indieAn.entity.user.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin
@Slf4j
public class MyPageController {

    @Autowired
    UserService userService;

    private String savePath = "C:\\workspace\\final_project\\IA-FE\\public\\tempImg\\";
    private String userPath = "C:\\workspace\\final_project\\IA-FE\\public\\img\\user\\";

    @ResponseBody
    @RequestMapping("/myPage")
    public ResponseEntity<ResponseTemplate> userPage(@RequestBody Member member){
        System.out.println(member);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        UserPageDto result = userService.userPageInfo(member);
        System.out.println(result);
        if(result != null) {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(result);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            response.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
        }

    }

    @ResponseBody
    @RequestMapping("/myPage/update")
    public ResponseEntity<ResponseTemplate> updateUser(@RequestBody UpdatePageDto result){

        System.out.println(result.getUserNo());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        userService.updateUser(result);

        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping("/myPage/board")
    public ResponseEntity<ResponseTemplate> userBoardHistory(@RequestParam(name="userNo") int userNo){
        System.out.println(userNo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        List<BoardDto> blist = userService.userBoardHistory(userNo);

        if(blist != null) {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(blist);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            response.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
        }

    }

    @ResponseBody
    @RequestMapping("/myPage/fund")
    public ResponseEntity<ResponseTemplate> userFundOrderHistory(@RequestParam(name="userNo") int userNo){
        System.out.println(userNo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        List<UserFundOrderDto> folist = userService.userFundOrderHistory(userNo);

        if(folist != null) {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(folist);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            response.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping("/myPage/fund/reward")
    public ResponseEntity<ResponseTemplate> userRewardOrderHistory(@RequestParam(name="userNo") int userNo,
                                                                   @RequestParam(name="fundNo") int fundNo) {
        System.out.println(userNo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        List<UserRewardOrderDto> rolist = userService.userRewardOrderHistory(userNo, fundNo);

        if(rolist != null) {
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(rolist);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            response.setStatus(StatusEnum.FAIL);
            return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping("/tempImg")
    public ResponseEntity<ResponseTemplate> tempImg(@RequestParam(value="image") MultipartFile image) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        String orgName = image.getOriginalFilename();

        File saveDirectory = new File(savePath);
        if (!saveDirectory.exists()) {
            saveDirectory.mkdirs(); // 경로가 존재하지 않으면 디렉토리를 생성
        }

        String currTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int randNum = (int)(Math.random() * 90000 + 10000);
        String ext = orgName.substring(orgName.lastIndexOf("."));

        String chgName = currTime + randNum + ext;

        image.transferTo(new File(savePath + chgName));
        log.info("저장경로 {}", savePath+chgName);

        response.setStatus(StatusEnum.SUCCESS);
        response.setData(chgName);
        log.info("응답값? {}",response.getData());
        log.info("진짜 주소? {}", savePath+response.getData());

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/imgDelete")
    public ResponseEntity<ResponseTemplate> imgDelete(@RequestBody String[] imgList) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();


        for (int i = 0; i < imgList.length; i++) {
            log.info("{}",savePath + imgList[i]);
            new File(savePath + imgList[i]).delete();
            // userPath쪽에서 저장된 파일 외에도 지워야 할 것
        }

        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/imgMove")
//    public ResponseEntity<ResponseTemplate> imgMove(@RequestParam("usedImage") String usedImage, @RequestParam("value=userNo") int userNo) throws IOException {
    public ResponseEntity<ResponseTemplate> imgMove(@RequestBody Member member) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        String nUserPath = userPath+member.getUserNo()+"\\";
        log.info("nUserPath? {}", nUserPath);

        File userSaveDirectory = new File(nUserPath);

        if (!userSaveDirectory.exists()) {
            userSaveDirectory.mkdirs(); // 경로가 존재하지 않으면 디렉토리를 생성
        }

        File[] existingFiles = userSaveDirectory.listFiles();
        if (existingFiles != null) {
            for (File file : existingFiles) {
                file.delete();
            }
        }


        Files.move(Paths.get(savePath+member.getUserProfileImg())
                , Paths.get(nUserPath+member.getUserProfileImg())
                , StandardCopyOption.ATOMIC_MOVE);

        response.setStatus(StatusEnum.SUCCESS);
        response.setData(nUserPath);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


}
