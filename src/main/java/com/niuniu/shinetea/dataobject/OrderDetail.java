package com.niuniu.shinetea.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    private String orderId;

    private Integer productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productSpecification;

    private String productPicture;

}
