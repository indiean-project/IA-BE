package com.ia.indieAn.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user_role")
@Data
public class UserRole {

    @Id
    private int roleNo;

    private String roleName;
}
