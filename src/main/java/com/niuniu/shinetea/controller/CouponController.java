package com.niuniu.shinetea.controller;

import com.niuniu.shinetea.VO.ResultVO;
import com.niuniu.shinetea.dataobject.SellerCoupon;
import com.niuniu.shinetea.enums.OrderTypeEnum;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.form.CouponForm;
import com.niuniu.shinetea.service.impl.SellerCouponServiceImpl;
import com.niuniu.shinetea.utils.KeyUtil;
import com.niuniu.shinetea.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

@RestController
@RequestMapping("/coupon")
@Slf4j
public class CouponController {

    @Autowired
    SellerCouponServiceImpl sellerCouponService;

    @PostMapping("/create")
    public ResultVO create(@Valid CouponForm couponForm,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【创建优惠券】参数不正确，couponForm={}",couponForm);
            throw new ShineTeaException(ResultEnum.PARAM_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        SellerCoupon sellerCoupon = new SellerCoupon();
        sellerCoupon.setCouponCode(KeyUtil.getCouponKey());
        sellerCoupon.setDiscount(couponForm.getDiscount());
        sellerCoupon.setUseCondition(couponForm.getUseCondition());
        sellerCoupon.setStartTime(couponForm.getStartTime());
        sellerCoupon.setEndTime(couponForm.getEndTime());
        sellerCoupon.setOrderType(couponForm.getOrderType());

        sellerCouponService.save(sellerCoupon);
        return ResultVOUtil.success();
    }

    //通过优惠码兑换
    /*@PostMapping("/receive1")
    public ResultVO receive1(@RequestParam("couponCode") String couponCode,
                             @RequestParam("memberId") Integer memberId) {

    }*/
}
