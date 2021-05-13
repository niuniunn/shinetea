package com.niuniu.shinetea.dataobject;

import com.niuniu.shinetea.enums.SellerCouponStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@DynamicUpdate
@Data
public class SellerCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer couponId;

    private BigDecimal useCondition;

    private String startTime;

    private String endTime;

    private BigDecimal discount;

    private Integer orderType;

    private String couponCode;

    private Integer couponStatus = SellerCouponStatusEnum.COUPON_UNRECEIVED.getCode();

}
