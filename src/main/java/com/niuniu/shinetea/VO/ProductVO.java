package com.niuniu.shinetea.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {

    private String categoryName;

    private Integer categoryType;

    private String categoryIcon;

    @JsonProperty("productList")
    private List<ProductInfoVO> productInfoVOList;
}
