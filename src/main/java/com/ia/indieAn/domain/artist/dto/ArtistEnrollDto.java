package com.ia.indieAn.domain.artist.dto;

import com.ia.indieAn.entity.user.Member;
import lombok.Data;

import java.sql.Date;

@Data
public class ArtistEnrollDto {

    private int userNo;

    private Date debutDate;

    private String artistName;

    private String musicCategory;

    private String artistInfo;

    private String artistStatus;

    private String youtubeLink;

    private String instagramLink;
}
