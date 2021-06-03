package com.niuniu.shinetea.controller;

import com.niuniu.shinetea.VO.ResultVO;
import com.niuniu.shinetea.dataobject.BuyerCoupon;
import com.niuniu.shinetea.dataobject.MemberInfo;
import com.niuniu.shinetea.dataobject.SellerCoupon;
import com.niuniu.shinetea.enums.BuyerCouponStatusEnum;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.enums.SellerCouponStatusEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.form.CouponForm;
import com.niuniu.shinetea.service.impl.BuyerCouponServiceImpl;
import com.niuniu.shinetea.service.impl.MemberInfoServiceImpl;
import com.niuniu.shinetea.service.impl.SellerCouponServiceImpl;
import com.niuniu.shinetea.utils.KeyUtil;
import com.niuniu.shinetea.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/coupon")
@Slf4j
public class CouponController {

    @Autowired
    SellerCouponServiceImpl sellerCouponService;

    @Autowired
    BuyerCouponServiceImpl buyerCouponService;

    @Autowired
    MemberInfoServiceImpl memberInfoService;

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

        SellerCoupon result = sellerCouponService.save(sellerCoupon);
        return ResultVOUtil.success(result.getCouponCode());
    }

    //通过优惠码兑换
    @PostMapping("/receive1")
    public ResultVO receive1(@RequestParam("couponCode") String couponCode,
                             @RequestParam("memberId") Integer memberId) {
        //查找该优惠码对应的优惠券是否存在
        SellerCoupon sellerCoupon = sellerCouponService.findByCouponCode(couponCode);
        if(sellerCoupon == null) {
            return ResultVOUtil.error(ResultEnum.COUPON_NOT_EXIST.getCode(),ResultEnum.COUPON_NOT_EXIST.getMessage());
        } else {
            // 检查状态是否为未领取
            if(sellerCoupon.getCouponStatus().equals(SellerCouponStatusEnum.COUPON_UNRECEIVED.getCode())) {
                //未领取  创建一条BuyerCoupon
                BuyerCoupon buyerCoupon = new BuyerCoupon();
                BeanUtils.copyProperties(sellerCoupon, buyerCoupon);
                buyerCoupon.setCouponId(null);
                buyerCoupon.setMemberId(memberId);
                buyerCoupon.setCouponStatus(BuyerCouponStatusEnum.UNUSED_COUPON.getCode());
                buyerCouponService.save(buyerCoupon);
                //更新sellerCoupon的状态为已领取  并返回领取成功
                sellerCoupon.setCouponStatus(SellerCouponStatusEnum.COUPON_RECEIVED.getCode());
                sellerCouponService.save(sellerCoupon);
                return ResultVOUtil.success();
            } else {
                //已领取  返回该优惠码已使用
                return ResultVOUtil.error(ResultEnum.COUPON_CODE_USED.getCode(), ResultEnum.COUPON_CODE_USED.getMessage());
            }
        }

    }

    //通过积分商城兑换
    @PostMapping("/receive2")
    public ResultVO receive2(@Valid CouponForm couponForm,
                             @RequestParam("memberId") Integer memberId,
                             @RequestParam("points") Integer points,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【领取优惠券】参数不正确，couponForm={}",couponForm);
            throw new ShineTeaException(ResultEnum.PARAM_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        //检查用户积分是否足够
        MemberInfo memberInfo = memberInfoService.findById(memberId);
        if(memberInfo == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMessage());
        }
        if(memberInfo.getPoints() >= points) {
            //积分足够 创建buyercoupon
            BuyerCoupon buyerCoupon = new BuyerCoupon();
            BeanUtils.copyProperties(couponForm, buyerCoupon);
            buyerCoupon.setMemberId(memberId);
            buyerCoupon.setCouponStatus(BuyerCouponStatusEnum.UNUSED_COUPON.getCode());
            buyerCouponService.save(buyerCoupon);
            //扣除积分
            memberInfoService.updatePoints(memberId,-points);
            return ResultVOUtil.success();
        } else {
            //积分不足
            return ResultVOUtil.error(ResultEnum.POINTS_NOT_ENOUGH.getCode(), ResultEnum.POINTS_NOT_ENOUGH.getMessage());
        }
    }
}
