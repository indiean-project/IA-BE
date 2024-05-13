package com.ia.indieAn.entity.board;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ia.indieAn.entity.artist.Artist;
import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.converter.ContentTypeConverter;
import com.ia.indieAn.type.enumType.ContentTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.ManyToAny;

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
@Table(name = "board")
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

    @JsonIgnoreProperties({"board"})
    @OneToMany(mappedBy = "board")
    private List<Reply> replies = new ArrayList<>();

    @Override
    public String toString() {
        return "Board{" +
                "boardNo=" + boardNo +
                ", userNo=" + member.getUserNo() +
                ", contentTypeNo=" + contentTypeNo +
                ", enrollDate=" + enrollDate +
                ", updateDate=" + updateDate +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                ", deleteYn='" + deleteYn + '\'' +
                ", viewCount=" + viewCount +
                ", artistNo=" + artist.getArtistNo() +
                ", boardColo=" + boardColo +
                ", replies=" + replies.size() +
                '}';
    }
}
