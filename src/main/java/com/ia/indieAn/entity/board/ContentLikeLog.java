package com.ia.indieAn.entity.board;

import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.converter.BrTypeConverter;
import com.ia.indieAn.type.enumType.BrcTypeEnum;
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
@Table(name = "content_like_log")
public class ContentLikeLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeLog;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private Member member;

    @Column(columnDefinition = "char(1) default 'Y'")
    private String likeYn;

    @Column(nullable = false)
    private int contentNo;
    /*
    * board_no, reply_no에 해당 되지만 2개 테이블의 기본키를 외래키로
    * 지정할 수 없기 때문에 물리적으론 외래키 지정하지 않음
    */

    @Convert(converter = BrTypeConverter.class)
    @Column(nullable = false)
    private BrcTypeEnum brType; //게시글(B)인지 댓글(R)인지 구분

    @Override
    public String toString() {
        return "ContentLikeLog{" +
                "likeLog=" + likeLog +
                ", userNo=" + member.getUserNo() +
                ", likeYn='" + likeYn + '\'' +
                ", contentNo=" + contentNo +
                ", brType=" + brType +
                '}';
    }
}
