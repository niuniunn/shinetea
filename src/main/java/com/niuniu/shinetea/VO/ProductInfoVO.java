package com.niuniu.shinetea.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("picture")
    private String productPicture;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("specification")
    private String productSpecification;
}
