package com.ia.indieAn.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "question")
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionNo; //getter, setter 메소드 camelCase 때문에 qNo -> questionNo로 바꿨습니다.

    @ManyToOne
    @JoinColumn(name = "user_no")
    private Member member;

    @Column(columnDefinition = "char(1) default 'N'")
    private String ansYn;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date questionDate;

    @Temporal(TemporalType.DATE)
    private Date ansDate;

    @Column(nullable = false)
    @Size(max = 4000)
    @Pattern(regexp = "^(?!\\s*$).+", message = "내용은 공백일 수 없습니다.")
    private String questionContent;

    @Size(max = 4000)
    @Pattern(regexp = "^(?!\\s*$).+", message = "내용은 공백일 수 없습니다.")
    private String ansContent;

    @Override
    public String toString() {
        return "Question{" +
                "questionNo=" + questionNo +
                ", userNo=" + (member != null ? member.getUserNo() : "null") +
                ", ansYn='" + ansYn + '\'' +
                ", questionDate=" + questionDate +
                ", ansDate=" + ansDate +
                ", questionContent='" + questionContent + '\'' +
                ", ansContent='" + ansContent + '\'' +
                '}';
    }
}
