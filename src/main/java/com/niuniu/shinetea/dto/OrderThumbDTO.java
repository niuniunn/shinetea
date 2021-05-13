package com.niuniu.shinetea.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderThumbDTO {

    private String orderId;

    private String shopName;

    private Integer orderStatus;

    private Date createTime;

    private BigDecimal actualPayment;

    private Integer quantity;

    private List<String> pictureList;
}
