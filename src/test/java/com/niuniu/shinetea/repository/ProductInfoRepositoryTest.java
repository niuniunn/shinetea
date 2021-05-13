package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.ProductInfo;
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
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("多肉芒芒");
        productInfo.setProductPrice(new BigDecimal(22));
        productInfo.setProductDescription("采用新鲜海南芒果，搭配咸香奶盖");
        productInfo.setProductPicture("www.pic.jpg");
        productInfo.setProductSpecification("[{title:甜度}]");
        productInfo.setCategoryType(14122);
        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findById() {
        ProductInfo productInfo = repository.findById(2).orElse(null);
        System.out.println(productInfo.toString());
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = repository.findByProductStatus(1);
        System.out.println(productInfoList.size());
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void delete() {
        repository.deleteById(2);
    }

    @Test
    public void findAll() {
        List<ProductInfo> productInfoList = repository.findAll();
        System.out.println(productInfoList.size());
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findByNameAndTypeAndStatus() {
        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> productInfoPage = repository.findByNameAndTypeAndStatus("多肉",14122,1,request);
        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }
}