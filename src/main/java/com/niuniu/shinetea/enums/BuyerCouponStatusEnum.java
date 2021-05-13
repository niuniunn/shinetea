package com.niuniu.shinetea.enums;

import lombok.Getter;

@Getter
public enum BuyerCouponStatusEnum {
    USED_COUPON(0, "已使用"),
    UNUSED_COUPON(1, "未使用"),
    ;

    private Integer code;

    private String message;

    BuyerCouponStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
