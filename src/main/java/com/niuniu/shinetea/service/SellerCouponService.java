package com.niuniu.shinetea.service;

import com.niuniu.shinetea.dataobject.SellerCoupon;

public interface SellerCouponService {

    SellerCoupon save(SellerCoupon sellerCoupon);

    void updateStatus(String couponCode);

}
