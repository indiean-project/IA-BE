package com.ia.indieAn.entity.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "content_type")
@Data
public class ContentType {

    @Id
    private int contentTypeNo;

    private String contentTypeName;
}
