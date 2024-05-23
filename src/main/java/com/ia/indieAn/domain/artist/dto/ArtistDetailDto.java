package com.ia.indieAn.domain.artist.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArtistDetailDto {

    private int artistNo;

    private int userNo;

    private Date debutDate;

    private String artistName;

    private String musicCategory;

    private String artistInfo;

    private String youtubeLink;

    private String instagramLink;

    private String titleUrl;

}
