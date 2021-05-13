package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.dto.OrderDTO;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.service.BuyerService;
import com.niuniu.shinetea.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findById(orderId);
        if(orderDTO == null) {
            return null;
        }
        //判断是否是自己的订单
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单openid不一致,openid={},orderDTO={}",openid,orderDTO);
            throw new ShineTeaException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
