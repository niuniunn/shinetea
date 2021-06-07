package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.OrderMaster;
import com.niuniu.shinetea.enums.OrderTypeEnum;
import com.niuniu.shinetea.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderMaster> orderMasterPage = repository.findByBuyerOpenidOrderByCreateTimeDesc("opUoK43Uf01PD6udLVMO7NGCtDkc", request);
        System.out.println(orderMasterPage.getTotalElements());
        Assert.assertNotEquals(0, orderMasterPage.getTotalElements());
    }

    @Test
    public void findByConditions() throws ParseException {
        String startTime = "2021-01-25";
        String endTime = "2021-05-09";   //查出来的信息包括开始日期 不包含结束日期
        Date time1 = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
        Date time2 = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
        PageRequest request = PageRequest.of(0,2);
        Page<OrderMaster> orderMasterPage = repository.findByConditions("15826",0,time1,time2,request);
        System.out.println(orderMasterPage.getTotalElements());
        Assert.assertNotEquals(0,orderMasterPage.getTotalElements());
    }

    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(KeyUtil.getUniqueKey());
        orderMaster.setOrderType(OrderTypeEnum.SHOP_RECEIVE_ORDER.getCode());
        orderMaster.setShopId(1);
        orderMaster.setShopName("成信大店");
        orderMaster.setBuyerPhone("18030565437");
        orderMaster.setBuyerGender(1);
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerAddress("成都信息工程大学12栋");
        orderMaster.setLatitude("30.57915");
        orderMaster.setLongitude("103.981");
        orderMaster.setCode(KeyUtil.getCode());
        orderMaster.setBuyerOpenid("opUoK43Uf01PD6udLVMO7NGCtDkc");
        orderMaster.setOrderAmount(new BigDecimal(12));
        orderMaster.setDiscount(new BigDecimal(0));
        orderMaster.setShippingFee(new BigDecimal(0));
        orderMaster.setActualPayment(new BigDecimal(12));
        orderMaster.setRemark("备注");
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }
}