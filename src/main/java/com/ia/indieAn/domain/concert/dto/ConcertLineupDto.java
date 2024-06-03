package com.ia.indieAn.domain.concert.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConcertLineupDto {

    private String artistName;

    private int artistNo;

    private String titleUrl;

    public static ConcertLineupDto convertToLineupDto(LineupPorjection lp){
        if(lp.getArtistNo() != null){
            return builder()
                    .artistName(lp.getArtistName())
                    .artistNo(lp.getArtistNo())
                    .titleUrl(lp.getImgUrl())
                    .build();
        }else{
            return builder()
                    .artistName(lp.getArtistName())
                    .build();
        }


    }
}
