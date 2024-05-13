package com.ia.indieAn.entity.board;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "board_colo")
public class BoardColo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coloNo;

    @OneToOne
    @JoinColumn(name = "board_no")
    private Board board;

    @Column(nullable = false)
    private String colRightTitle;

    @Column(nullable = false)
    private String colLeftTitle;

    @JsonIgnoreProperties({"boardColo"})
    @OneToMany(mappedBy = "boardColo")
    private List<ColoLog> coloLogList = new ArrayList<>();

    @Override
    public String toString() {
        return "BoardColo{" +
                "coloNo=" + coloNo +
                ", boardNo=" + board.getBoardNo() +
                ", colRightTitle='" + colRightTitle + '\'' +
                ", colLeftTitle='" + colLeftTitle + '\'' +
                ", coloLogList=" + coloLogList.size() +
                '}';
    }
}
