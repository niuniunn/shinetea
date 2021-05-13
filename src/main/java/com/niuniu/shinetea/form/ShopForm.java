package com.niuniu.shinetea.form;

import com.niuniu.shinetea.enums.ShopStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ShopForm {

    private Integer id;

    @NotBlank(message = "门店名称不能为空")
    private String name;

    @NotBlank(message = "经度不能为空")
    private String longitude;

    @NotBlank(message = "纬度不能为空")
    private String latitude;

    @NotBlank(message = "地址不能为空")
    private String address;

    @NotBlank(message = "门店详细地址不能为空")
    private String detail;

    @NotBlank(message = "电话不能为空")
    private String tel;

    @NotBlank(message = "门店开始营业时间不能为空")
    private String startTime;

    @NotBlank(message = "门店闭店时间不能为空")
    private String endTime;

    private String remark;
}
