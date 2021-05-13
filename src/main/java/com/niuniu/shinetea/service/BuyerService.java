package com.niuniu.shinetea.service;

import com.niuniu.shinetea.dto.OrderDTO;

public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);
}
