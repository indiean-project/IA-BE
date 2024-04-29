package com.ia.indieAn.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@DynamicInsert
@Entity
@Table(name = "member")
@Data
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_m")
    @SequenceGenerator(sequenceName = "seq_mem", allocationSize = 1, name = "seq_m")
    private int userNo;

    private int roleNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String nickname;
    private String phone;
    private String address;

    private LocalDateTime createDate;

    @Temporal(TemporalType.DATE)
    private Date deleteDate;

    @ColumnDefault("N")
    private String deleteYn;

    @ColumnDefault("N")
    private String reportStatus;

    private String userProfileImg;
    private String userContent;
    private String userFavoriteArtist;
    private String userFavoriteMusic;




}
