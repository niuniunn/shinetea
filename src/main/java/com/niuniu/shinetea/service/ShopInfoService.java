package com.niuniu.shinetea.service;

import com.niuniu.shinetea.dataobject.ShopInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShopInfoService {

    ShopInfo findById(Integer shopId);

    List<ShopInfo> findAll();

    ShopInfo save(ShopInfo shopInfo);

    void delete(Integer shopId);

    void updateStatus(Integer shopId, Integer isOpen);

    Page<ShopInfo> findByNameAndAddressAndStatus(String shopName, String address, Integer isOpen, Pageable pageable);
}
