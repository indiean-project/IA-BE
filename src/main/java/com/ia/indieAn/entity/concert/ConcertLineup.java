package com.ia.indieAn.entity.concert;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;

@DynamicInsert
@Entity
@Table(name = "concert_lineup")
@Data
public class ConcertLineup implements Serializable {

    @Id
    private int lineupNo;

    @ManyToOne
    @JoinColumn(name = "concert_no")
    private Concert concert;

    @Column(nullable = false)
    private String artistName;
}
