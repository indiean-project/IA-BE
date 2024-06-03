package com.ia.indieAn.domain.concert.dto;

import com.ia.indieAn.entity.artist.Artist;
import com.ia.indieAn.entity.concert.Concert;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class SearchLineupDto {

    private int lineupNo;

    private int concertNo;

    private String artistName;

    private int artistNo;

}
