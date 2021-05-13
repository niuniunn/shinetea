package com.niuniu.shinetea.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Data
public class ProductForm {

    private Integer id;

    @NotBlank(message = "商品名称不能为空")
    private String name;

    @NotNull(message = "商品价格不能为空")
    private BigDecimal price;

    @NotBlank(message = "商品描述不能为空")
    private String desc;

    @NotBlank(message = "商品图片不能为空")
    private String picture;

    @NotBlank(message = "商品规格不能为空")
    private String specification;

    @NotNull(message = "商品分类不能为空")
    private Integer type;

}
