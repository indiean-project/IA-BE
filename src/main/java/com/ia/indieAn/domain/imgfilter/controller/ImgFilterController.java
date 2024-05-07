package com.ia.indieAn.domain.imgfilter.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/imgfilter")
@CrossOrigin
public class ImgFilterController {
    private String savePath = "C:\\workspace\\IndieAn\\IA-FE\\public\\tempImg\\";
    private String newPath = "C:\\workspace\\IndieAn\\IA-FE\\public\\img\\";

    @RequestMapping("/tempImg")
    public String tempImg(@RequestParam(value="image") MultipartFile image) throws IOException {
        String orgName = image.getOriginalFilename();

        String currTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int randNum = (int)(Math.random() * 90000 + 10000);
        String ext = orgName.substring(orgName.lastIndexOf("."));

        String chgName = currTime + randNum + ext;

        image.transferTo(new File(savePath + chgName));

        return chgName;
    }

    @RequestMapping("/imgDelete")
    public String imgDelete(@RequestBody String[] imgList) {
        for (int i = 0; i < imgList.length; i++) {
            new File(savePath + imgList[i]).delete();
        }

        return "";
    }

    @RequestMapping("/imgMove")
    public String imgMove(@RequestBody String[] imgList) throws IOException {
        for (int i = 0; i < imgList.length; i++) {
            Files.move(Paths.get(savePath + imgList[i])
            , Paths.get(newPath + imgList[i])
            , StandardCopyOption.ATOMIC_MOVE);
        }

        return "";
    }
}
