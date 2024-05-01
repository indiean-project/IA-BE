package com.ia.indieAn.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@DynamicInsert
@Entity
@Table(name = "member")
@Data
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNo;

    @ColumnDefault("2")
    private int roleNo;

    private String userId;
    private String userPwd;
    private String userName;
    private String nickName;
    private String phone;
    private String address;

    @CreationTimestamp
    private Timestamp createDate;

    @Temporal(TemporalType.DATE)
    private Date deleteDate;

    @ColumnDefault("'N'")
    private String deleteYn;

    @ColumnDefault("'N'")
    private String reportStatus;

    private String userProfileImg;
    private String userContent;
    private String userFavoriteArtist;
    private String userFavoriteMusic;

}
