package com.ia.indieAn.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.board.ContentLikeLog;
import com.ia.indieAn.entity.board.ContentReportLog;
import com.ia.indieAn.entity.fund.Fund;
import com.ia.indieAn.entity.fund.FundLog;
import com.ia.indieAn.entity.fund.OrderLog;
import com.ia.indieAn.type.converter.UserRoleConverter;
import com.ia.indieAn.type.enumType.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "member")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 시퀀스 생성 및 할당
    private int userNo;

    @Convert(converter = UserRoleConverter.class)
    @Column(columnDefinition = "integer default 2")
    private UserRoleEnum userRole;

    @Column(unique = true, nullable = false)
    @Email
    private String userId;

    @Column
    @Pattern(regexp = "^$|^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&+=])(?!.*\\s).{6,16}$", message = "비밀번호는 최소 6자 이상이어야 하며, 알파벳, 숫자, 특수 문자를 포함해야 합니다.")
    private String userPwd;

    @Column(nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String nickname;

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

    @Column(columnDefinition = "char(1) default 'N'")
    private String socialStatus;

    private String userProfileImg;
    private String userContent;
    private String userFavoriteArtist;
    private String userFavoriteMusic;

    //참조하는 엔티티에 대한 매핑
    //컬럼이 생성되지는 않음(조회용)

    //문의
    @JsonIgnoreProperties({"member"})
    @OneToMany(mappedBy = "member") //mappedBy 해당 테이블의 member 객체에만 권한을 갖겠다
    private List<Question> questionList = new ArrayList<>(); //member 테이블에서 question 테이블을 수정할 수 없도록 방지

    //좋아요
    @JsonIgnoreProperties({"member"})
    @OneToMany(mappedBy = "member")
    private List<ContentLikeLog> contentLikeLogList = new ArrayList<>();

    //펀딩 신청 내역
    @JsonIgnoreProperties({"member"})
    @OneToMany(mappedBy = "member")
    private List<Fund> fundList = new ArrayList<>();

    //펀딩 참가 내역
    @JsonIgnoreProperties({"member"})
    @OneToMany(mappedBy = "member")
    private List<FundLog> fundLogList = new ArrayList<>();

    //주문 내역
    @JsonIgnoreProperties({"member"})
    @OneToMany(mappedBy = "member")
    private List<OrderLog> orderLogList = new ArrayList<>();

    //신고 내역
    @JsonIgnoreProperties({"member"})
    @OneToMany(mappedBy = "member")
    private List<ContentReportLog> contentReportLogList = new ArrayList<>();

    @JsonIgnoreProperties({"member"})
    @OneToMany(mappedBy = "member")
    private List<Board> boardList = new ArrayList<>();

    @Override
    public String toString() {
        return "Member{" +
                "userNo=" + userNo +
                ", userRole=" + userRole +
                ", userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userName='" + userName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", createDate=" + createDate +
                ", deleteDate=" + deleteDate +
                ", deleteYn='" + deleteYn + '\'' +
                ", reportStatus='" + reportStatus + '\'' +
                ", socialStatus='" + socialStatus + '\'' +
                ", userProfileImg='" + userProfileImg + '\'' +
                ", userContent='" + userContent + '\'' +
                ", userFavoriteArtist='" + userFavoriteArtist + '\'' +
                ", userFavoriteMusic='" + userFavoriteMusic + '\'' +
                ", questionList=" + questionList +
                ", contentLikeLogList=" + contentLikeLogList +
                ", fundList=" + fundList.size() +
                ", fundLogList=" + fundLogList.size() +
                ", orderLogList=" + orderLogList.size() +
                ", contentReportLogList=" + contentReportLogList.size() +
                ", boardList=" + boardList.size() +
                '}';
    }
}
