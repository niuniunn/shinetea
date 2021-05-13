package com.niuniu.shinetea.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    private String id;

    private String username;

    private String password;
}
