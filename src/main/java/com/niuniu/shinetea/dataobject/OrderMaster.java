package com.niuniu.shinetea.dataobject;

import com.niuniu.shinetea.enums.OrderStatusEnum;
import com.niuniu.shinetea.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
@Proxy(lazy = false)
public class OrderMaster {

    @Id
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

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    private Integer payStatus = PayStatusEnum.SUCCESS.getCode();  //没有支付功能  默认直接支付成功

    private Date createTime;
}
