package com.ia.indieAn.entity.board;

import com.ia.indieAn.entity.artist.Artist;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.converter.ContentTypeConverter;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.ManyToAny;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@Entity
@Table(name = "board")
@Data
public class Board implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNo;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private Member member;

    @Convert(converter = ContentTypeConverter.class)
    @Column(nullable = false)
    private ContentTypeEnum contentTypeNo;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date enrollDate;

    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @Column(nullable = false)
    private String boardTitle;

    @Column(nullable = false)
    private String boardContent;

    @Column(columnDefinition = "char(1) default 'N'")
    private String deleteYn;

    private int viewCount;

    @ManyToOne
    @JoinColumn(name = "artist_no", nullable = true)
    private Artist artist;

    @OneToOne(mappedBy = "board")
    private BoardColo boardColo;

    @OneToMany(mappedBy = "board")
    private List<Reply> replies = new ArrayList<>();
}
