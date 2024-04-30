package com.ia.indieAn.entity.fund;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "fund_type")
@Data
public class FundType {

    @Id
    private int fundTypeNo;
    private String fundTypeName;
}
