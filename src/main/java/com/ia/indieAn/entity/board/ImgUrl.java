package com.ia.indieAn.entity.board;

import com.ia.indieAn.type.converter.FabcTypeConverter;
import com.ia.indieAn.type.converter.KcTypeConverter;
import com.ia.indieAn.type.enumType.FabcTypeEnum;
import com.ia.indieAn.type.enumType.KcTypeEnum;
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
@Table(name = "img_url")
public class ImgUrl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imgNo;

    @Column(nullable = false)
    private String imgUrl;

    //Enum 작업
    @Convert(converter = FabcTypeConverter.class)
    @Column(nullable = false)
    private FabcTypeEnum fabcType; //F(펀딩), A(아티스트), B(게시글), C(콘서트)

    @Convert(converter = KcTypeConverter.class)
    @Column(nullable = false)
    private KcTypeEnum kcType;

    @Column(nullable = false)
    private int contentNo;
    /*
     * board_no, reply_no에 해당 되지만 2개 테이블의 기본키를 외래키로
     * 지정할 수 없기 때문에 물리적으론 외래키 지정하지 않음
     */

    @Override
    public String toString() {
        return "ImgUrl{" +
                "imgNo=" + imgNo +
                ", imgUrl='" + imgUrl + '\'' +
                ", fabcType=" + fabcType +
                ", kcType=" + kcType +
                ", contentNo=" + contentNo +
                '}';
    }
}
