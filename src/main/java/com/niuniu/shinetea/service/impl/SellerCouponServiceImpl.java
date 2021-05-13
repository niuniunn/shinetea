package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.SellerCoupon;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.enums.SellerCouponStatusEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.repository.SellerCouponRepository;
import com.niuniu.shinetea.service.SellerCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerCouponServiceImpl implements SellerCouponService {

    @Autowired
    private SellerCouponRepository repository;

    @Override
    public SellerCoupon save(SellerCoupon sellerCoupon) {
        return repository.save(sellerCoupon);
    }

    @Override
    public void updateStatus(String couponCode) {
        SellerCoupon sellerCoupon = repository.findByCouponCode(couponCode);
        if(sellerCoupon == null) {
            throw new ShineTeaException(ResultEnum.COUPON_CODE_ERROR);
        }
        sellerCoupon.setCouponStatus(SellerCouponStatusEnum.COUPON_RECEIVED.getCode());
        repository.save(sellerCoupon);
    }
}
