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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.RepositoryQuery;
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
        String changeUrl = artistEnrollDto.getArtistInfo().replace("<img src=\"/public/tempImg", "<img src=\"/public/img");
        artistEnrollDto.setArtistInfo(changeUrl);
        Artist artist = artistService.artistEnroll(artistEnrollDto);
        if(artist != null){
            response.setStatus(StatusEnum.SUCCESS);
            response.setData(artist.getArtistNo());
        }else{
            response.setStatus(StatusEnum.FAIL);
        }
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


    @RequestMapping("/confirmation")
    public ResponseEntity<ResponseTemplate> confirmation(@RequestParam (value = "userNo") int userNo){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        Artist artist = artistService.confirmation(userNo);
        if(artist !=null) {
            response.setStatus(StatusEnum.SUCCESS);
            switch (artist.getArtistStatus()) {
                case "N":
                    response.setData("심사중인 회원입니다.");
                    break;
                case "R":
                    response.setData("반려되었습니다. 관리자에 문의해주세요.");
                    break;
                case "A":
                    response.setData("등록된 아티스트입니다.");
                    break;
            }
        }else{
            response.setStatus(StatusEnum.FAIL);
        }
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @RequestMapping("/home")
    public ResponseEntity<ResponseTemplate> getHomeArtist(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
        ResponseTemplate response = new ResponseTemplate();

        response.setData(artistService.getHomeArtist());
        response.setStatus(StatusEnum.SUCCESS);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
