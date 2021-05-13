package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.ProductInfo;
import com.niuniu.shinetea.enums.ProductStatusEnum;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.repository.ProductInfoRepository;
import com.niuniu.shinetea.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findById(Integer productId) {
        return productInfoRepository.findById(productId).orElse(null);
    }

    @Override
    public List<ProductInfo> findAll() {
        return productInfoRepository.findAll();
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void delete(Integer productId) {
        productInfoRepository.deleteById(productId);
    }

    @Override
    public void updateStatus(Integer productId, Integer productStatus) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElse(null);
        if(productInfo == null) {
            throw new ShineTeaException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        productInfo.setProductStatus(productStatus);
        productInfoRepository.save(productInfo);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findByNameAndTypeAndStatus(String productName, Integer categoryType, Integer productStatus, Pageable pageable) {
        Page<ProductInfo> productInfoPage = productInfoRepository.findByNameAndTypeAndStatus(productName,categoryType,productStatus,pageable);
        List<ProductInfo> productInfoList = productInfoPage.getContent();
        return new PageImpl<>(productInfoList,pageable,productInfoPage.getTotalElements());
    }

    @Override
    public Page<ProductInfo> findByNameAndType(String productName, Integer categoryType, Pageable pageable) {
        Page<ProductInfo> productInfoPage = productInfoRepository.findByNameAndType(productName,categoryType,pageable);
        List<ProductInfo> productInfoList = productInfoPage.getContent();
        return new PageImpl<>(productInfoList,pageable,productInfoPage.getTotalElements());
    }
}
