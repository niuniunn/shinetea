package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.OrderDetail;
import com.niuniu.shinetea.dataobject.OrderMaster;
import com.niuniu.shinetea.dto.OrderDTO;
import com.niuniu.shinetea.dto.OrderThumbDTO;
import com.niuniu.shinetea.enums.OrderTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "opUoK43Uf01PD6udLVMO7NGCtDkc";
    private final String ORDER_ID = "1620545722710522458";
    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderType(OrderTypeEnum.SHOP_RECEIVE_ORDER.getCode());
        orderDTO.setShopId(1);
        orderDTO.setShopName("空港华联店");
        orderDTO.setBuyerName("Chincoo");
        orderDTO.setBuyerPhone("18030565437");
        orderDTO.setBuyerGender(1);
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setOrderAmount(new BigDecimal(38));
        orderDTO.setDiscount(new BigDecimal(5));
        orderDTO.setActualPayment(new BigDecimal(33));
        orderDTO.setRemark("备注");

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId(1);
        o1.setProductQuantity(2);
        o1.setProductSpecification("");

        OrderDetail o2 = new OrderDetail();
        o2.setProductId(3);
        o2.setProductQuantity(1);
        o2.setProductSpecification("");

        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);

        log.info("【创建订单】result={}", result);
        Assert.assertNotNull(result);

    }

    @Test
    public void findById() {
        OrderDTO result = orderService.findById(ORDER_ID);
        log.info("【查询单个订单】result={}",result);
        Assert.assertEquals(ORDER_ID, result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request = PageRequest.of(0,2);
//        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, request);
        Page<OrderThumbDTO> orderThumbDTOPage = orderService.findList(BUYER_OPENID, request);
        Assert.assertNotEquals(0, orderThumbDTOPage.getTotalElements());
    }

    @Test
    public void findByConditions() throws ParseException {
        PageRequest request = PageRequest.of(0,2);
        String startTime = "2021-01-25";
        String endTime = "2021-05-09";   //查出来的信息包括开始日期 不包含结束日期
        Date time1 = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
        Date time2 = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
        Page<OrderDTO> orderDTOPage = orderService.findByConditions("162", 1, 0, time1, time2, request);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void updateStatus() {
        OrderMaster orderMaster = orderService.updateStatus(ORDER_ID, 1);
        Assert.assertNotNull(orderMaster);
    }
}