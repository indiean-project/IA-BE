package com.ia.indieAn.entity.fund;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@DynamicInsert
@Entity
@Table(name = "order_log")
@Data
public class OrderLog {

    @Id
    private int orderLogNo;
    private int userNo;
    private int fundNo;
    private int totalPrice;
    private String receiptId;
    private String billingKey;
    private LocalDateTime orderDate;
    private LocalDateTime paymentDate;

}
