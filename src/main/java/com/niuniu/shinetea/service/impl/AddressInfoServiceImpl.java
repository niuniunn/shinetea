package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.AddressInfo;
import com.niuniu.shinetea.repository.AddressInfoRepository;
import com.niuniu.shinetea.service.AddressInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressInfoServiceImpl implements AddressInfoService {

    @Autowired
    private AddressInfoRepository addressInfoRepository;

    @Override
    public List<AddressInfo> findByMemberId(Integer memberId) {
        return addressInfoRepository.findByMemberId(memberId);
    }

    @Override
    public AddressInfo save(AddressInfo addressInfo) {
        return addressInfoRepository.save(addressInfo);
    }

    @Override
    public void delete(Integer addressId) {
        addressInfoRepository.deleteById(addressId);
    }

    @Override
    public AddressInfo findById(Integer addressId) {
        return addressInfoRepository.findById(addressId).orElse(null);
    }
}
