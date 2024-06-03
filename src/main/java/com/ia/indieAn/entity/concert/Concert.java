package com.ia.indieAn.entity.concert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ia.indieAn.domain.concert.dto.ConcertEnrollDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@Builder
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
    @Size(max = 4000)
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
    public static Concert convertFormConcertEnrollDto(ConcertEnrollDto c){
        return Concert.builder()
                .concertTitle(c.getConcertTitle())
                .location(c.getLocation())
                .startDate(c.getStartDate())
                .concertPrice(c.getConcertPrice())
                .runtime(c.getRuntime())
                .endDate(c.getEndDate())
                .concertInfo(c.getConcertInfo())
                .ticketUrl(c.getTicketUrl())
                .build();
    }
}
