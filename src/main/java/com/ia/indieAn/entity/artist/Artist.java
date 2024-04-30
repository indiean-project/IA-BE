package com.ia.indieAn.entity.artist;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@DynamicInsert
@Entity
@Table(name = "artist")
@Data
public class Artist {

    @Id
    private int artistNo;

    private int userNo;
    private String artistName;
    private String musicCategory;
    private String artistInfo;
    private LocalDateTime debutDate;
}
