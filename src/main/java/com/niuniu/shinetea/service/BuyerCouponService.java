package com.niuniu.shinetea.service;

import com.niuniu.shinetea.dataobject.BuyerCoupon;

import java.util.List;

public interface BuyerCouponService {

    //买家领取优惠券  save
    BuyerCoupon save(BuyerCoupon buyerCoupon);


    //买家使用优惠券  updateStatus
    void useCoupon(Integer couponId);

    //获取买家所有优惠券
    List<BuyerCoupon> findByMemberId(Integer memberId);
}
