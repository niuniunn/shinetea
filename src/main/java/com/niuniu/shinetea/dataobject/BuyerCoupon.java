package com.niuniu.shinetea.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class BuyerCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer couponId;

    private Integer memberId;

    private BigDecimal useCondition;

    private String startTime;

    private String endTime;

    private BigDecimal discount;

    private Integer orderType;

    private Integer couponStatus;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT")
    private Date receiveTime;

    private String useTime;
}
