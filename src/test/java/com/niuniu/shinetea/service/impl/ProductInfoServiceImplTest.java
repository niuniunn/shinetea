package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.ProductInfo;
import com.niuniu.shinetea.enums.ProductStatusEnum;
import com.niuniu.shinetea.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoServiceImpl productInfoService;

    @Test
    public void findById() {
        ProductInfo productInfo = productInfoService.findById(3);
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findAll() {
        List<ProductInfo> productInfoList = productInfoService.findAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("多肉芒芒");
        productInfo.setProductPrice(new BigDecimal(22));
        productInfo.setProductDescription("采用新鲜海南芒果，搭配咸香奶盖");
        productInfo.setProductPicture("www.pic.jpg");
        productInfo.setProductSpecification("[{title:甜度}]");
        productInfo.setCategoryType(14122);
        ProductInfo result = productInfoService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void delete() {
        productInfoService.delete(4);
    }

    @Test
    public void updateStatus() {
        productInfoService.updateStatus(1, ProductStatusEnum.DOWN.getCode());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findByNameAndTypeAndStatus() {
        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> productInfoPage = productInfoService.findByNameAndTypeAndStatus("多肉",14122,1,request);
        System.out.println(productInfoPage.getTotalElements());
    }
}