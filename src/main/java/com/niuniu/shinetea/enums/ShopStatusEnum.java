package com.niuniu.shinetea.enums;

import lombok.Getter;

@Getter
public enum ShopStatusEnum {
    OPEN(1, "营业中"),
    CLOSE(0, "歇业中"),
    ;
    private Integer code;
    private String message;

    ShopStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
