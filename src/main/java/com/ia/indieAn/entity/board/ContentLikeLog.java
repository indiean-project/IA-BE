package com.ia.indieAn.entity.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Entity
@Table(name = "content_like_log")
@Data
public class ContentLikeLog {

    @Id
    private int likeLog;

    private int userNo;

    @ColumnDefault("'Y'")
    private String likeYn;

    private int contentNo;
    private String brType;


}
