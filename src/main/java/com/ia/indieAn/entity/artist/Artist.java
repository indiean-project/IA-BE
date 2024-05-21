package com.ia.indieAn.entity.artist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.concert.ConcertLineup;
import com.ia.indieAn.entity.user.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
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

    private String musicCategory;

    private String artistInfo;

    private String artistStatus;

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
