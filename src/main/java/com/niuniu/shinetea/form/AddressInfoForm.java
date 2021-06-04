package com.niuniu.shinetea.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddressInfoForm {

    private Integer addressId;

    @NotNull(message = "会员编号不能为空")
    private Integer memberId;

    @NotBlank(message = "收货人名字不能为空")
    private String name;

    @NotNull(message = "性别不能为空")
    private Integer gender;

    @NotBlank(message = "详细地址不能为空")
    private String addressDetail;

    @NotBlank(message = "纬度不能为空")
    private String latitude;

    @NotBlank(message = "经度不能为空")
    private String longitude;

    @NotBlank(message = "电话号码不能为空")
    private String phoneNumber;
}
