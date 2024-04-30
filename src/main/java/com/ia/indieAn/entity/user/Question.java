package com.ia.indieAn.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@DynamicInsert
@Entity
@Table(name = "question")
@Data
public class Question {

    @Id
    private int questionNo; //getter, setter 메소드 camelCase 때문에 qNo -> questionNo로 바꿨습니다.

    private int userNo;
    private String questionContent;

    @ColumnDefault("N")
    private String ansYn;

    private String ansContent;
    private LocalDateTime questionDate;
    private LocalDateTime ansDate;
}
