package com.niuniu.shinetea.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1,"参数不正确"),
    CATEGORY_NOT_EXIST(2, "分类不存在"),
    SHOP_NOT_EXIST(3, "门店不存在"),
    PRODUCT_NOT_EXIST(4,"商品不存在"),
    COUPON_CODE_ERROR(5, "优惠码错误"),
    USER_NOT_EXIST(6,"用户不存在"),
    PASSWORD_ERROR(7,"密码错误"),
    ORDER_NOT_EXIST(8, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(9, "订单详情不存在"),
    CART_EMPTY(10, "购物车不能为空"),
    ORDER_OWNER_ERROR(11, "订单不属于当前用户"),
    COUPON_NOT_EXIST(12, "优惠券不存在"),
    COUPON_CODE_USED(13, "优惠码已使用"),
    POINTS_NOT_ENOUGH(14, "积分不足"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
