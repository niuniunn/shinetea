package com.niuniu.shinetea.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@DynamicUpdate
@Proxy(lazy = false)
public class AddressInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    private Integer memberId;

    private String name;

    private Integer gender;

    private String addressDetail;

    private String latitude;

    private String longitude;

    private String phoneNumber;
}
