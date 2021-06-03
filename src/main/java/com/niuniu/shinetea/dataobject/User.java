package com.niuniu.shinetea.dataobject;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Proxy(lazy = false)
public class User {

    @Id
    private String id;

    private String username;

    private String password;
}
