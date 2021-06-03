package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.ShopInfo;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.repository.ShopInfoRepository;
import com.niuniu.shinetea.service.ShopInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ShopInfoServiceImpl implements ShopInfoService {

    @Autowired
    private ShopInfoRepository repository;

    @Override
    public ShopInfo findById(Integer shopId) {
        return repository.findById(shopId).orElse(null);
    }

    @Override
    public List<ShopInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public ShopInfo save(ShopInfo shopInfo) {
        return repository.save(shopInfo);
    }

    @Override
    public Page<ShopInfo> findByNameAndAddressAndStatus(String shopName, String address, Integer isOpen, Pageable pageable) {
        Page<ShopInfo> shopInfoPage = repository.findByNameAndAddressAndStatus(shopName,address,isOpen,pageable);
        List<ShopInfo> shopInfoList = shopInfoPage.getContent();
        return new PageImpl<>(shopInfoList,pageable,shopInfoPage.getTotalElements());
    }

    @Override
    public Page<ShopInfo> findByNameAndAddress(String shopName, String address, Pageable pageable) {
        Page<ShopInfo> shopInfoPage = repository.findByNameAndAddress(shopName, address, pageable);
        List<ShopInfo> shopInfoList = shopInfoPage.getContent();
        return new PageImpl<>(shopInfoList,pageable,shopInfoPage.getTotalElements());
    }

    @Override
    public void delete(Integer shopId) {
        repository.deleteById(shopId);
    }

    @Override
    public void updateStatus(Integer shopId, Integer isOpen) {
        ShopInfo shopInfo = repository.findById(shopId).orElse(null);
        if(shopInfo == null) {
            throw new ShineTeaException(ResultEnum.SHOP_NOT_EXIST);
        }
        shopInfo.setIsOpen(isOpen);
        repository.save(shopInfo);
    }
}
