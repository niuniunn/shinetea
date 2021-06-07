package com.niuniu.shinetea.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderThumbDTO {

    private String orderId;

    private String shopName;

    private Integer orderStatus;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT")
    private Date createTime;

    private BigDecimal actualPayment;

    private Integer quantity;

    private List<String> pictureList;
}
