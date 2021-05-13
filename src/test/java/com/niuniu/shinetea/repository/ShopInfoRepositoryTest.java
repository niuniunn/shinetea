package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.ShopInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopInfoRepositoryTest {

    @Autowired
    private ShopInfoRepository repository;

    @Test
    public void findByNameAndAddressAndStatus() {
        PageRequest request = PageRequest.of(1,1);
//        Page<ShopInfo> shopInfoPage = repository.findByNameAndIdAndAddressAndStatus("空港",1,"",1,request);
        Page<ShopInfo> shopInfoPage = repository.findByNameAndAddressAndStatus("成信","",1, request);
        System.out.println(shopInfoPage.getTotalElements());
        Assert.assertNotEquals(0,shopInfoPage.getTotalElements());
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0,3);
        Page<ShopInfo> shopInfoPage = repository.findAll(request);
        System.out.println(shopInfoPage.getTotalElements());
        Assert.assertNotEquals(0,shopInfoPage.getTotalElements());
    }
}