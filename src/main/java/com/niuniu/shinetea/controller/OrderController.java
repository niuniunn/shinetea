package com.niuniu.shinetea.controller;

import com.niuniu.shinetea.VO.ResultVO;
import com.niuniu.shinetea.converter.OrderForm2OrderDTOConverter;
import com.niuniu.shinetea.dataobject.MemberInfo;
import com.niuniu.shinetea.dataobject.PointRecord;
import com.niuniu.shinetea.dto.OrderDTO;
import com.niuniu.shinetea.dto.OrderThumbDTO;
import com.niuniu.shinetea.dto.PageDTO;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.form.OrderForm;
import com.niuniu.shinetea.service.*;
import com.niuniu.shinetea.service.impl.BuyerCouponServiceImpl;
import com.niuniu.shinetea.service.impl.PointRecordServiceImpl;
import com.niuniu.shinetea.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private MemberInfoService memberInfoService;

    @Autowired
    private BuyerCouponServiceImpl buyerCouponService;

    @Autowired
    private PointRecordServiceImpl pointRecordService;

    //创建订单
    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new ShineTeaException(ResultEnum.PARAM_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new ShineTeaException(ResultEnum.CART_EMPTY);
        }
        //外送订单 收货地址不能为空

        OrderDTO createResult = orderService.create(orderDTO);
        //积分增加
        MemberInfo memberInfo = memberInfoService.findByOpenid(orderForm.getBuyerOpenid());
        memberInfoService.updatePoints(memberInfo.getMemberId(),orderForm.getActualPayment().intValue());

        //写入一条积分记录
        PointRecord pointRecord = new PointRecord();
        pointRecord.setMemberId(memberInfo.getMemberId());
        pointRecord.setReason("购买商品");
        pointRecord.setVariation(orderForm.getActualPayment().intValue());
        pointRecordService.save(pointRecord);
        //使用了优惠券进行状态更改
        if(orderForm.getCouponId() != null) {
            buyerCouponService.useCoupon(orderForm.getCouponId());
        }
        Map<String,String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    //用户获取自己的订单列表
    @PostMapping("/list")
    public ResultVO list(@RequestParam("openid") String openid,
                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if(ObjectUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new ShineTeaException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = PageRequest.of(page-1,size);
        Page<OrderThumbDTO> orderDTOPage = orderService.findList(openid,request);

        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setSize(size);
        pageDTO.setData(orderDTOPage.getContent());
        pageDTO.setTotal(orderDTOPage.getTotalElements());

        return ResultVOUtil.success(pageDTO);
    }

    //小程序用户查询订单详情
    @PostMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //管理员查询订单详情
    @PostMapping("/managedetail")
    public ResultVO managedetail(@RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = orderService.findById(orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //修改订单状态
    @PostMapping("/status")
    public ResultVO changeStatus(@RequestParam("orderId") String orderId,
                                 @RequestParam("orderStatus") Integer orderStatus) {
        if(orderService.findById(orderId) == null) {
            return ResultVOUtil.error(ResultEnum.ORDER_NOT_EXIST.getCode(), ResultEnum.ORDER_NOT_EXIST.getMessage());
        }
        orderService.updateStatus(orderId, orderStatus);
        return ResultVOUtil.success();
    }

    //分页条件查询
    @PostMapping("/page")
    public ResultVO pageList(@RequestParam("orderId") String orderId,
                             @RequestParam("startTime") String startTime,
                             @RequestParam("endTime") String endTime,
                             @RequestParam("orderStatus") Integer orderStatus,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) throws ParseException {
        PageRequest request = PageRequest.of(page-1, size);
        Page<OrderDTO> orderDTOPage;

        Date time1 = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
        Date time2 = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
        if(orderStatus == -1) {
            orderDTOPage = orderService.findByConditionsExceptOrderStatus(orderId, time1, time2,request);
        } else {
            orderDTOPage = orderService.findByConditions(orderId, orderStatus, time1, time2, request);
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setData(orderDTOPage.getContent());
        pageDTO.setPage(page);
        pageDTO.setSize(size);
        pageDTO.setTotal(orderDTOPage.getTotalElements());
        return ResultVOUtil.success(pageDTO);
    }
}
