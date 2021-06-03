package com.niuniu.shinetea.service;

import com.niuniu.shinetea.dataobject.OrderMaster;
import com.niuniu.shinetea.dto.OrderDTO;
import com.niuniu.shinetea.dto.OrderThumbDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);

    OrderDTO findById(String orderId);

    /*用户查询自己的订单列表*/
    Page<OrderThumbDTO> findList(String buyerOpenid, Pageable pageable);

    /*管理员条件查询订单*/
    Page<OrderDTO> findByConditions(String orderId, Integer orderStatus,
                                    Date startTime, Date endTime, Pageable pageable);

    Page<OrderDTO> findByConditionsExceptOrderStatus(String orderId, Date startTime, Date endTime, Pageable pageable);

    OrderMaster updateStatus(String orderId, Integer orderStatus);
}
