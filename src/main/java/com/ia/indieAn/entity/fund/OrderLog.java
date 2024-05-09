package com.ia.indieAn.entity.fund;

import com.ia.indieAn.entity.user.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
@Entity
@Table(name = "order_log")
public class OrderLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderLogNo;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "fund_no")
    private Fund fund;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private String receiptId;

    @Column(nullable = false)
    private String billingKey;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date paymentDate;

    @Override
    public String toString() {
        return "OrderLog{" +
                "orderLogNo=" + orderLogNo +
                ", userNo=" + member.getUserNo() +
                ", fundNo=" + fund.getFundNo() +
                ", totalPrice=" + totalPrice +
                ", receiptId='" + receiptId + '\'' +
                ", billingKey='" + billingKey + '\'' +
                ", orderDate=" + orderDate +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
