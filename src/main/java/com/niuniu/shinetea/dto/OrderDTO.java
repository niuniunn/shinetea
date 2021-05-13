package com.niuniu.shinetea.dto;

import com.niuniu.shinetea.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private String orderId;

    private Integer orderType;

    private Integer shopId;

    private String shopName;

    private String buyerName;

    private String buyerPhone;

    private Integer buyerGender;

    private String buyerAddress;

    private String latitude;

    private String longitude;

    private String code;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private BigDecimal discount;

    private Integer couponId;

    private BigDecimal shippingFee;

    private BigDecimal actualPayment;

    private String remark;

    private Integer orderStatus ;

    private Integer payStatus;

    private Date createTime;

    private List<OrderDetail> orderDetailList;
}
