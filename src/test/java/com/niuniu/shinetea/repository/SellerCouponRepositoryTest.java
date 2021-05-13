package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.SellerCoupon;
import com.niuniu.shinetea.enums.OrderTypeEnum;
import com.niuniu.shinetea.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerCouponRepositoryTest {

    @Autowired
    private SellerCouponRepository repository;

    @Test
    public void findByCouponCode() {
        SellerCoupon sellerCoupon = repository.findByCouponCode("abcdefg");
        Assert.assertNotNull(sellerCoupon);
    }

    @Test
    public void create() {
        SellerCoupon sellerCoupon = new SellerCoupon();
        sellerCoupon.setCouponCode(KeyUtil.getCouponKey());
        sellerCoupon.setDiscount(new BigDecimal(5));
        sellerCoupon.setUseCondition(new BigDecimal(40));
        sellerCoupon.setStartTime("2021-04-20");
        sellerCoupon.setEndTime("2021-05-30");
        sellerCoupon.setOrderType(OrderTypeEnum.COMMON_USE_ORDER.getCode());

        SellerCoupon result = repository.save(sellerCoupon);
        Assert.assertNotNull(result);
    }
}