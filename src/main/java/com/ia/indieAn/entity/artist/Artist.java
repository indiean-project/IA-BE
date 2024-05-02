package com.ia.indieAn.entity.artist;

import com.ia.indieAn.entity.board.Board;
import com.ia.indieAn.entity.user.Member;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@Entity
@Table(name = "artist")
@Data
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

    @OneToMany(mappedBy = "artist")
    private List<Board> boards = new ArrayList<>();
}
