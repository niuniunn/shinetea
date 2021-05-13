package com.niuniu.shinetea.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CouponForm {

    @NotNull(message = "使用条件不能为空")
    private BigDecimal useCondition;

    @NotBlank(message = "起止时间不能为空")
    private String startTime;

    @NotBlank(message = "截止时间不能为空")
    private String endTime;

    @NotNull(message = "优惠金额不能为空")
    private BigDecimal discount;

    @NotNull(message = "使用订单类型不能为空")
    private Integer orderType;
}
