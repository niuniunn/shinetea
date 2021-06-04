package com.niuniu.shinetea.service;

import com.niuniu.shinetea.dataobject.AddressInfo;

import java.util.List;

public interface AddressInfoService {
    List<AddressInfo> findByMemberId(Integer memberId);

    AddressInfo save(AddressInfo addressInfo);

    void delete(Integer addressId);

    AddressInfo findById(Integer addressId);
}
