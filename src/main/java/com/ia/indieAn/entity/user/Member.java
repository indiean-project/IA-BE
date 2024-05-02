package com.ia.indieAn.entity.user;

import com.ia.indieAn.type.converter.UserRoleConverter;
import com.ia.indieAn.type.enumType.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@Entity
@Table(name = "member")
@Data
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNo;

    @Convert(converter = UserRoleConverter.class)
    @Column(columnDefinition = "integer default 2")
    private UserRoleEnum userRole;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPwd;

    @Column(nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String nickName;

    @Column(unique = true, nullable = false)
    private String phone;

    private String address;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Temporal(TemporalType.DATE)
    private Date deleteDate;

    @Column(columnDefinition = "char(1) default 'N'")
    private String deleteYn;

    @Column(columnDefinition = "char(1) default 'N'")
    private String reportStatus;

    private String userProfileImg;
    private String userContent;
    private String userFavoriteArtist;
    private String userFavoriteMusic;

    //참조하는 엔티티에 대한 매핑

    //문의
    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();

}
