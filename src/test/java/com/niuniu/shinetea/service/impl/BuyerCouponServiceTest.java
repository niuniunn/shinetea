package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.BuyerCoupon;
import com.niuniu.shinetea.enums.BuyerCouponStatusEnum;
import com.niuniu.shinetea.enums.OrderTypeEnum;
import com.niuniu.shinetea.service.BuyerCouponService;
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
public class BuyerCouponServiceTest {

    @Autowired
    private BuyerCouponService buyerCouponService;

    @Test
    public void save() {
        BuyerCoupon buyerCoupon = new BuyerCoupon();
        buyerCoupon.setMemberId(1);
        buyerCoupon.setUseCondition(new BigDecimal(20));
        buyerCoupon.setStartTime("2021-05-10");
        buyerCoupon.setEndTime("2021-06-10");
        buyerCoupon.setDiscount(new BigDecimal(3));
        buyerCoupon.setOrderType(OrderTypeEnum.COMMON_USE_ORDER.getCode());
        buyerCoupon.setCouponStatus(BuyerCouponStatusEnum.UNUSED_COUPON.getCode());
        BuyerCoupon result = buyerCouponService.save(buyerCoupon);
        Assert.assertNotNull(result);
    }

    @Test
    public void useCoupon() {
        buyerCouponService.useCoupon(3);
    }

    @Test
    public void findByMemberId() {
        List<BuyerCoupon> buyerCouponList = buyerCouponService.findByMemberId(1);
        Assert.assertNotNull(buyerCouponList);
    }
}