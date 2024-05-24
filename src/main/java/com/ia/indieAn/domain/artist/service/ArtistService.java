package com.ia.indieAn.domain.artist.service;

import com.ia.indieAn.common.exception.CustomException;
import com.ia.indieAn.common.exception.ErrorCode;
import com.ia.indieAn.domain.artist.dto.*;
import com.ia.indieAn.domain.artist.repository.ArtistRepository;
import com.ia.indieAn.domain.imgurl.repository.ImgUrlRepository;
import com.ia.indieAn.domain.user.repository.UserRepository;
import com.ia.indieAn.entity.artist.Artist;
import com.ia.indieAn.entity.board.ImgUrl;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.enumType.FabcTypeEnum;
import com.ia.indieAn.type.enumType.KcTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ImgUrlRepository imgUrlRepository;
    private final UserRepository userRepository;


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

        Artist artist = artistRepository.findByArtistNoAndArtistStatus(artistNo,"Y")
                .orElseThrow(()->new CustomException(ErrorCode.ARTIST_NOT_FOUND));

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
    @Transactional
    public Artist artistEnroll(ArtistEnrollDto artistEnrollDto) {
        Member member = userRepository.findByUserNo(artistEnrollDto.getUserNo());
        Artist artist = Artist.builder()
                .member(member)
                .artistStatus(artistEnrollDto.getArtistStatus())
                .artistName(artistEnrollDto.getArtistName())
                .artistInfo(artistEnrollDto.getArtistInfo())
                .debutDate(artistEnrollDto.getDebutDate())
                .musicCategory(artistEnrollDto.getMusicCategory())
                .youtubeLink(artistEnrollDto.getYoutubeLink())
                .instagramLink(artistEnrollDto.getInstagramLink())
                .build();


        return artistRepository.save(artist);
    }
}
