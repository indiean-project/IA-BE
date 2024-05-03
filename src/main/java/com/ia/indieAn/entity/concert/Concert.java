package com.ia.indieAn.entity.concert;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@Entity
@Table(name = "concert")
@Data
public class Concert implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int concertNo;

    @Column(nullable = false)
    private String location;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(nullable = false)
    private String concertInfo;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(columnDefinition = "char(1) default 'N'")
    private String deleteYn;

    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @OneToMany(mappedBy = "concert")
    private List<ConcertLineup> concertLineupList = new ArrayList<>();
}
