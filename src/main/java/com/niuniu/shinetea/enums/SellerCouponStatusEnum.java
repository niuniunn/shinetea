package com.niuniu.shinetea.enums;

import lombok.Getter;

@Getter
public enum SellerCouponStatusEnum {

    COUPON_RECEIVED(0, "已被领取"),
    COUPON_UNRECEIVED(1, "未被领取"),
    ;

    private Integer code;

    private String message;

    SellerCouponStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
