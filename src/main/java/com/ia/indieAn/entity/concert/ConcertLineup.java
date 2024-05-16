package com.ia.indieAn.entity.concert;

import com.ia.indieAn.entity.artist.Artist;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "concert_lineup")
public class ConcertLineup implements Serializable {

    @Id
    private int lineupNo;

    @ManyToOne
    @JoinColumn(name = "concert_no")
    private Concert concert;

    @Column(nullable = false)
    private String artistName;

    @ManyToOne
    @JoinColumn(name = "artist_no")
    private Artist artist;

    @Override
    public String toString() {
        return "ConcertLineup{" +
                "lineupNo=" + lineupNo +
                ", concertNo=" + concert.getConcertNo() +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
