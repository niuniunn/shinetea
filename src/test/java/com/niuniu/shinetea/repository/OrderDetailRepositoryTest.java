package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.OrderDetail;
import com.niuniu.shinetea.utils.KeyUtil;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("1620209286948915826");
        System.out.println(orderDetailList.size());
        Assert.assertNotEquals(0,orderDetailList.size());
    }

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(KeyUtil.getUniqueKey());
        orderDetail.setOrderId("1620209286948915826");
        orderDetail.setProductId(1);
        orderDetail.setProductName("柠檬水");
        orderDetail.setProductPrice(new BigDecimal(8));
        orderDetail.setProductQuantity(2);
        orderDetail.setProductSpecification("[]");
        orderDetail.setProductPicture("www.pic.jpg");
        OrderDetail result = repository.save(orderDetail);
        System.out.println(result.toString());
        Assert.assertNotNull(result);
    }
}