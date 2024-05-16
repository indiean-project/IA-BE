package com.ia.indieAn.entity.board;

import com.ia.indieAn.entity.user.Member;
import com.ia.indieAn.type.converter.RlTypeConverter;
import com.ia.indieAn.type.enumType.RlTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "colo_log")
public class ColoLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coloLogNo;

    @ManyToOne
    @JoinColumn(name = "colo_no")
    private BoardColo boardColo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private Member member;

    @Convert(converter = RlTypeConverter.class)
    @Column(nullable = false)
    private RlTypeEnum vote; // R인지 L인지

    @Column(columnDefinition = "char(1) default 'N'")
    private String cancelYn;

    @Override
    public String toString() {
        return "ColoLog{" +
                "coloLogNo=" + coloLogNo +
                ", coloNo=" + boardColo.getColoNo() +
                ", userNo=" + member.getUserNo() +
                ", vote='" + vote + '\'' +
                ", cancelYn='" + cancelYn + '\'' +
                '}';
    }
}
