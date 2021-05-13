package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOne() {
        ProductCategory productCategory = repository.findById(1).orElse(null);
        System.out.println(productCategory.toString());
        Assert.assertNotNull(productCategory);
    }

    @Test
    //@Transactional   //使数据库更干净  不会改变数据库 测试完成立即回滚
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("新品尝鲜",14127,"www.pic.jpg");
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);  //检验是否为空
    }

    @Test
    public void findAllTest() {
        List<ProductCategory> productCategoryList = repository.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeTest() {
        List<Integer> list = Arrays.asList(14122,14123);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void deleteByCategoryId() {
        repository.deleteById(13);
    }
}