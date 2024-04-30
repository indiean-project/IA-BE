package com.ia.indieAn.entity.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Entity
@Table(name = "img_url")
@Data
public class ImgUrl {

    @Id
    private int imgNo;
    private String imgUrl;
    private String fabcType; //펩시 타입이라고 부릅시다.
    private String kcType;
    private int contentNo;
}
