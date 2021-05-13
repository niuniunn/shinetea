package com.niuniu.shinetea.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryForm {

    private Integer categoryId;

    @NotNull(message = "分类编号必填")
    private Integer categoryType;

    @NotBlank(message = "分类名称不能为空")
    private String categoryName;

    @NotBlank(message = "图片地址不能为空")
    private String categoryIcon;

}
