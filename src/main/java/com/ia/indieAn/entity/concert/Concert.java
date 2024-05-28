package com.ia.indieAn.entity.concert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "concert")
public class Concert implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int concertNo;

    @Column(nullable = false)
    private String concertTitle;

    @Column(nullable = false)
    private String location;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(nullable = false)
    private int concertPrice;

    private String runtime;

    private String ticketUrl;

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

    @JsonIgnoreProperties({"concert"})
    @OneToMany(mappedBy = "concert")
    private List<ConcertLineup> concertLineupList = new ArrayList<>();

    @Override
    public String toString() {
        return "Concert{" +
                "concertNo=" + concertNo +
                ", location='" + location + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", concertInfo='" + concertInfo + '\'' +
                ", createDate=" + createDate +
                ", deleteYn='" + deleteYn + '\'' +
                ", updateDate=" + updateDate +
                ", concertLineupList=" + concertLineupList.size() +
                '}';
    }
}
