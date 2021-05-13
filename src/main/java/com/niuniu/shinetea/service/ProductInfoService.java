package com.niuniu.shinetea.service;

import com.niuniu.shinetea.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    ProductInfo findById(Integer productId);

    List<ProductInfo> findAll();

    ProductInfo save(ProductInfo productInfo);

    void delete(Integer productId);

    //禁用 启用
    void updateStatus(Integer productId, Integer productStatus);

    //查询所有在架商品
    List<ProductInfo> findUpAll();

    //按照名称 分类 状态 模糊分页查询
    Page<ProductInfo> findByNameAndTypeAndStatus(String productName, Integer categoryType, Integer productStatus, Pageable pageable);

    //查询条件没有状态
    Page<ProductInfo> findByNameAndType(String productName, Integer categoryType, Pageable pageable);
}
