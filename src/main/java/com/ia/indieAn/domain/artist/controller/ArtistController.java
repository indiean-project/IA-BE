package com.ia.indieAn.domain.artist.controller;
import com.ia.indieAn.common.responseEntity.ResponseTemplate;
import com.ia.indieAn.common.responseEntity.StatusEnum;
import com.ia.indieAn.domain.artist.dto.ArtistDetailDto;
import com.ia.indieAn.domain.artist.dto.ArtistDto;
import com.ia.indieAn.domain.artist.dto.ArtistEnrollDto;
import com.ia.indieAn.domain.artist.dto.ArtistSearchDto;
import com.ia.indieAn.domain.artist.service.ArtistService;
import com.ia.indieAn.entity.artist.Artist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/artist")
@Slf4j
public class ArtistController {


    private final ArtistService artistService;


    @RequestMapping("/artistList")
    public ResponseEntity<ResponseTemplate> artistList(@RequestBody ArtistSearchDto artistSearchDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        List<ArtistDto> result= artistService.artistList(artistSearchDto);
        response.setData(result);
        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/detail")
    public ResponseEntity<ResponseTemplate> artistDetail(@RequestParam(value = "artistNo") int artistNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();


        ArtistDetailDto result = artistService.artistDetail(artistNo);
        response.setData(result);
        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    @RequestMapping("/enroll")
    public ResponseEntity<ResponseTemplate> artistEnroll(@RequestBody ArtistEnrollDto artistEnrollDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();
        Artist artist = artistService.artistEnroll(artistEnrollDto);

        if(artist != null){
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(artist.getArtistNo());
        }else{
            response.setStatus(StatusEnum.FAIL);
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}