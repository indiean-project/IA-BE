package com.ia.indieAn.domain.artist.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
import com.ia.indieAn.domain.artist.dto.ArtistDetailDto;
import com.ia.indieAn.domain.artist.dto.ArtistDto;
import com.ia.indieAn.domain.artist.dto.ArtistDtoProjection;
import com.ia.indieAn.domain.artist.dto.ArtistSearchDto;
import com.ia.indieAn.domain.artist.repository.ArtistRepository;
import com.ia.indieAn.domain.imgurl.repository.ImgUrlRepository;
import com.ia.indieAn.entity.artist.Artist;
import com.ia.indieAn.entity.board.ImgUrl;
import com.ia.indieAn.type.enumType.FabcTypeEnum;
import com.ia.indieAn.type.enumType.KcTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ImgUrlRepository imgUrlRepository;


    public List<ArtistDto> artistList(ArtistSearchDto artistSearchDto) {

        Pageable pageable = PageRequest.of(0, artistSearchDto.getSize());
        List<ArtistDto> artistDtoList = new ArrayList<ArtistDto>();

        if(artistSearchDto.getSort().equals("createDate")) {
            Slice<ArtistDtoProjection> page = artistRepository.findByArtistListCreate(pageable,artistSearchDto.getKeyword());
            Slice<ArtistDto> ArtistDtoPage = page.map(ArtistDto::conventToArtistDto);
            artistDtoList = ArtistDtoPage.getContent();
        }else{
            Slice<ArtistDtoProjection> page = artistRepository.findByArtistListDebut(pageable,artistSearchDto.getKeyword());
            Slice<ArtistDto> ArtistDtoPage = page.map(ArtistDto::conventToArtistDto);
            artistDtoList = ArtistDtoPage.getContent();
        }

        return artistDtoList;
    }

    public ArtistDetailDto artistDetail(int artistNo) {

        Artist artist = artistRepository.findByArtistNoAndArtistStatus(artistNo,"Y");
        if(artist == null){
            throw new CustomException(ErrorCode.ARTIST_NOT_FOUND);
        }

        ArrayList<ImgUrl> imgUrl = imgUrlRepository.findByContentNoAndFabcTypeAndKcType(artistNo,FabcTypeEnum.ARTIST,KcTypeEnum.KING);

        ArtistDetailDto artistDetailDto = ArtistDetailDto.builder()
                .artistNo(artist.getArtistNo())
                .artistInfo(artist.getArtistInfo())
                .artistName(artist.getArtistName())
                .userNo(artist.getMember().getUserNo())
                .debutDate(artist.getDebutDate())
                .musicCategory(artist.getMusicCategory())
                .instagramLink(artist.getInstagramLink())
                .youtubeLink(artist.getYoutubeLink())
                .build();
        if(imgUrl.size()>0) {
            artistDetailDto.setTitleUrl(imgUrl.get(0).getImgUrl());
        }


        return artistDetailDto;
    }
}
