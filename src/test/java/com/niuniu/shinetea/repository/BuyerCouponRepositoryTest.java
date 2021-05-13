package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.BuyerCoupon;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerCouponRepositoryTest {

    @Autowired
    private BuyerCouponRepository repository;

    @Test
    public void findByMemberId() {
        List<BuyerCoupon> buyerCouponList = repository.findByMemberId(1);
        Assert.assertNotNull(buyerCouponList);
    }
}