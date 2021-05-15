package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dataobject.BuyerCoupon;
import com.niuniu.shinetea.enums.BuyerCouponStatusEnum;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.repository.BuyerCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BuyerCouponServiceImpl implements com.niuniu.shinetea.service.BuyerCouponService {

    @Autowired
    private BuyerCouponRepository repository;

    @Override
    public BuyerCoupon save(BuyerCoupon buyerCoupon) {
        return repository.save(buyerCoupon);
    }

    @Override
    public void useCoupon(Integer couponId) {
        BuyerCoupon buyerCoupon = repository.findById(couponId).orElse(null);
        if(buyerCoupon == null) {
            throw new ShineTeaException(ResultEnum.COUPON_NOT_EXIST);
        }
        buyerCoupon.setCouponStatus(BuyerCouponStatusEnum.USED_COUPON.getCode());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        buyerCoupon.setUseTime(df.format(new Date()));
        repository.save(buyerCoupon);
    }

    @Override
    public List<BuyerCoupon> findByMemberId(Integer memberId) {
        return repository.findByMemberId(memberId);
    }
}
