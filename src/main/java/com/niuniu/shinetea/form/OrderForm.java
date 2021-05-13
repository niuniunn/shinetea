package com.niuniu.shinetea.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OrderForm {

    @NotNull(message = "订单类型不能为空")
    private Integer orderType;

    @NotNull(message = "店铺id不能为空")
    private Integer shopId;

    @NotBlank(message = "店铺名称不能为空")
    private String shopName;

    @NotBlank(message = "买家名称不能为空")
    private String buyerName;

    @NotBlank(message = "买家电话不能为空")
    private String buyerPhone;

    @NotNull(message = "买家性别不能为空")
    private Integer buyerGender;

    private String buyerAddress;

    private String latitude;

    private String longitude;

    @NotBlank(message = "买家openid不能为空")
    private String buyerOpenid;

    @NotNull(message = "订单总金额不能为空")
    private BigDecimal orderAmount;

    private BigDecimal discount;

    private Integer couponId;

    private BigDecimal shippingFee;

    @NotNull(message = "订单实付金额不能为空")
    private BigDecimal actualPayment;

    private String remark;

    @NotBlank(message = "商品不能为空")
    private String items;
}
