package com.ia.indieAn.domain.artist.service;

import com.ia.indieAn.domain.artist.dto.ArtistDto;
import com.ia.indieAn.domain.artist.dto.ArtistSearchDto;
import com.ia.indieAn.domain.artist.repository.ArtistRepository;
import com.ia.indieAn.domain.imgurl.repository.ImgUrlRepository;
import com.ia.indieAn.entity.artist.Artist;
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
        Pageable pageable = null;

        if(artistSearchDto.getSort().equals("createDate")) {
            pageable = PageRequest.of(0, artistSearchDto.getSize(), Sort.by(Sort.Direction.DESC, "artistNo"));
        }else{
            pageable = PageRequest.of(0, artistSearchDto.getSize(), Sort.by(Sort.Direction.ASC, "debutDate"));
        }
        Slice<Artist> pageArtist = artistRepository.findByArtistNameContaining(pageable, artistSearchDto.getKeyword());
        List<Artist> artistList = pageArtist.getContent();
        List<ArtistDto> artistDtoList = new ArrayList<ArtistDto>();
        for(int i =0 ; i <artistList.size();i++ ){
            artistDtoList.add(new ArtistDto(artistList.get(i),imgUrlRepository.findByContentNoAndFabcTypeAndKcType(artistList.get(i).getArtistNo(), FabcTypeEnum.ARTIST, KcTypeEnum.KING).getImgUrl()));
        }

        return artistDtoList;
    }
}
