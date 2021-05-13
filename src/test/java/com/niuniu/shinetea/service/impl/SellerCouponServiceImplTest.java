package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.SellerCoupon;
import com.niuniu.shinetea.enums.OrderTypeEnum;
import com.niuniu.shinetea.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerCouponServiceImplTest {

    @Autowired
    SellerCouponServiceImpl sellerCouponService;

    @Test
    public void save() {
        SellerCoupon sellerCoupon = new SellerCoupon();
        sellerCoupon.setCouponCode(KeyUtil.getCouponKey());
        sellerCoupon.setDiscount(new BigDecimal(5));
        sellerCoupon.setUseCondition(new BigDecimal(40));
        sellerCoupon.setStartTime("2021-04-20");
        sellerCoupon.setEndTime("2021-05-30");
        sellerCoupon.setOrderType(OrderTypeEnum.COMMON_USE_ORDER.getCode());

        sellerCouponService.save(sellerCoupon);
    }

    @Test
    public void updateStatus() {
        sellerCouponService.updateStatus("dmqWUZ7kPiqTXgDk");
    }
}