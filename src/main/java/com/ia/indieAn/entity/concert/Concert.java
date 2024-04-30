package com.ia.indieAn.entity.concert;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@DynamicInsert
@Entity
@Table(name = "concert")
@Data
public class Concert {

    @Id
    private int concertNo;

    private String location;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String concertInfo;
    private LocalDateTime createDate;

    @ColumnDefault("N")
    private String deleteYn;

    private LocalDateTime updateDate;

}
