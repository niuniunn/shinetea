package com.niuniu.shinetea.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
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

    private Date receiveTime;

    private String useTime;
}
