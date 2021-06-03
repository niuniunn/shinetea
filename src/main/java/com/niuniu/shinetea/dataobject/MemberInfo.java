package com.niuniu.shinetea.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
@Proxy(lazy = false)
public class MemberInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    private String openid;

    private String nickname;

    private String phoneNumber;

    private Integer gender;

    private String birthday;

    private BigDecimal balance = new BigDecimal(0);

    private Integer points = 0;
}
