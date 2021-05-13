package com.niuniu.shinetea.enums;

import lombok.Getter;

@Getter
public enum OrderTypeEnum {

    COMMON_USE_ORDER(0, "通用订单"),
    SHOP_RECEIVE_ORDER(1, "自取订单"),
    OUTSIDE_ORDER(2, "外送订单"),
    ;

    private Integer code;

    private String message;

    OrderTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
