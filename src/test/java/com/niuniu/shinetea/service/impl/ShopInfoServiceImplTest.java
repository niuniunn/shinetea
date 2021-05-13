package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.ShopInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopInfoServiceImplTest {

    @Autowired
    ShopInfoServiceImpl shopInfoService;

    @Test
    public void findById() {
        ShopInfo shopInfo = shopInfoService.findById(1);
        Assert.assertNotNull(shopInfo);
    }

    @Test
    public void save() {
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShopName("成信大店");
        shopInfo.setLongitude("103.987235");
        shopInfo.setLatitude("30.57173");
        shopInfo.setAddress("双流区航空港学府路一段");
        shopInfo.setAddressDetail("24号成都信息工程大学西一门");
        shopInfo.setOpenTime("10:00");
        shopInfo.setCloseTime("22:00");
        shopInfo.setPhoneNumber("18030565437");
        ShopInfo result = shopInfoService.save(shopInfo);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByNameAndAddressAndStatus() {
        PageRequest request = PageRequest.of(0,1);
        Page<ShopInfo> shopInfoPage = shopInfoService.findByNameAndAddressAndStatus("成信大","",1,request);
        System.out.println(shopInfoPage.getTotalPages());
        Assert.assertNotEquals(0,shopInfoPage.getTotalElements());
    }

    @Test
    public void delete() {
        shopInfoService.delete(2);
    }

    @Test
    public void updateStatus() {
        shopInfoService.updateStatus(5,1);
    }
}