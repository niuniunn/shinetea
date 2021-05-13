package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.SellerCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerCouponRepository extends JpaRepository<SellerCoupon, Integer> {
    SellerCoupon findByCouponCode(String couponCode);
}
