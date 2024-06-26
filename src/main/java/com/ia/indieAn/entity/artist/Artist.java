package com.ia.indieAn.entity.artist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.concert.ConcertLineup;
import com.ia.indieAn.entity.user.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Builder
@Getter @Setter
@DynamicInsert
@Entity
@Table(name = "artist")
public class Artist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artistNo;

    @OneToOne
    @JoinColumn(name = "user_no")
    private Member member;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date debutDate;

    @Column(nullable = false, unique = true)
    private String artistName;

    @Column(nullable = false)
    private String musicCategory;

    @Column(nullable = false)
    @Size(max = 4000)
    private String artistInfo;

    @Column(columnDefinition = "char(1) default 'N'")
    private String artistStatus;

    private String youtubeLink;

    private String instagramLink;

    @JsonIgnoreProperties({"artist"})
    @OneToMany(mappedBy = "artist")
    private List<Board> boards = new ArrayList<>();

    @JsonIgnoreProperties({"artist"})
    @OneToMany(mappedBy = "artist")
    private List<ConcertLineup> ConcertLineups = new ArrayList<>();



    @Override
    public String toString() {
        return "Artist{" +
                "artistNo=" + artistNo +
                ", userNo=" + member.getUserNo() +
                ", debutDate=" + debutDate +
                ", artistName='" + artistName + '\'' +
                ", musicCategory='" + musicCategory + '\'' +
                ", artistInfo='" + artistInfo + '\'' +
                ", boards=" + boards.size() +
                '}';
    }


}
