package com.ia.indieAn.domain.user.controller;

import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.config.email.EmailService;
import com.ia.indieAn.domain.user.dto.LoginUserDto;
import com.ia.indieAn.domain.user.dto.UpdatePageDto;
import com.ia.indieAn.domain.user.dto.UserPageDto;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.domain.user.service.UserService;
import jakarta.validation.Valid;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    private final EmailService emailService;

    private String savePath = "C:\\workspace\\final_project\\IA-FE\\public\\tempImg\\";
    private String userPath = "C:\\workspace\\final_project\\IA-FE\\public\\img\\user\\";

    public UserController(EmailService emailService) {
        this.emailService = emailService;
    }

    @ResponseBody
    @RequestMapping("/login")
    public ResponseEntity<ResponseTemplate> loginUser(@RequestBody Member member){
        System.out.println(member);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        LoginUserDto result = userService.loginUser(member);
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
    @RequestMapping("/signUp")
    public ResponseEntity<ResponseTemplate> signUpUser(@RequestBody Member member){
        log.info("enter /signUp {}", member);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        userService.signUpUser(member);
        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/signUp/checkId")
    public ResponseEntity<ResponseTemplate> checkUserId(@RequestBody @Valid Member member) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        userService.checkUserId(member);
        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping("/signUp/checkPwd")
    public ResponseEntity<ResponseTemplate> checkUserPwd(@RequestBody @Valid Member member) {
//        log.info("enter /signUp/checkPwd {}", member);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping("/signUp/sendEmail")
    public String sendVerifyEmail (@RequestParam("userId") String userId) throws Exception {
        log.info("인증아이디 : {}", userId);

        String code = emailService.sendVerifyMessage(userId);
        log.info("인증코드 : {}", code);
        return code;
    }

    // 백엔드에서도 인증 번호 입력 확인해야할 method 필요함

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

//        ArrayList<String> list = new ArrayList<>();

//        for (int i = 0; i < imgList.length; i++) {
//            Files.move(Paths.get(savePath + imgList[i])
//                    , Paths.get(nUserPath + imgList[i])
//                    , StandardCopyOption.ATOMIC_MOVE);
//            log.info("뭘까? {}", savePath+imgList[i]);
//            log.info("이건 뭔데? {}", nUserPath+imgList[i]);
//
//            list.add(nUserPath + imgList[i]);
//        }

//        response.setStatus(StatusEnum.SUCCESS);
//        response.setData(list);
