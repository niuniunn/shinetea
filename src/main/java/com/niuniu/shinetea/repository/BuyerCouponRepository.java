package com.niuniu.shinetea.repository;

import com.niuniu.shinetea.dataobject.BuyerCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyerCouponRepository extends JpaRepository<BuyerCoupon, Integer> {
    List<BuyerCoupon> findByMemberId(Integer memberId);
}
