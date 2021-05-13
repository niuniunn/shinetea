package com.niuniu.shinetea.service.impl;

import com.niuniu.shinetea.converter.OrderMaster2OrderDTOConverter;
import com.niuniu.shinetea.dataobject.OrderDetail;
import com.niuniu.shinetea.dataobject.OrderMaster;
import com.niuniu.shinetea.dataobject.ProductInfo;
import com.niuniu.shinetea.dto.OrderDTO;
import com.niuniu.shinetea.dto.OrderThumbDTO;
import com.niuniu.shinetea.enums.OrderStatusEnum;
import com.niuniu.shinetea.enums.OrderTypeEnum;
import com.niuniu.shinetea.enums.PayStatusEnum;
import com.niuniu.shinetea.enums.ResultEnum;
import com.niuniu.shinetea.exception.ShineTeaException;
import com.niuniu.shinetea.repository.OrderDetailRepository;
import com.niuniu.shinetea.repository.OrderMasterRepository;
import com.niuniu.shinetea.service.OrderService;
import com.niuniu.shinetea.service.ProductInfoService;
import com.niuniu.shinetea.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductInfoService productInfoService;

    @Override
    @Transactional   //出错回滚
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();
        String code = KeyUtil.getCode();
        //查询商品信息
        for(OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findById(orderDetail.getProductId());
            //将规格记录下来  被商品的规格覆盖后再赋值回来
            String specification = orderDetail.getProductSpecification();
            if(productInfo == null) {
                throw new ShineTeaException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            //将productInfo属性拷贝给orderDetail
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setProductSpecification(specification);
            orderDetailRepository.save(orderDetail);
        }
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        orderDTO.setCode(code);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());

        //个人积分增加
        //使用了优惠券进行扣除
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    public OrderDTO findById(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
        if(orderMaster == null) {
            throw new ShineTeaException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)) {
            throw  new ShineTeaException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderThumbDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        List<OrderThumbDTO> orderThumbDTOList = new ArrayList<>();
        for(OrderDTO orderDTO: orderDTOList) {
            Integer quantity = 0;
            List<String> pictureList = new ArrayList<>();
            List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderDTO.getOrderId());
            OrderThumbDTO orderThumbDTO = new OrderThumbDTO();
            BeanUtils.copyProperties(orderDTO, orderThumbDTO);
            for(OrderDetail orderDetail: orderDetailList) {
                quantity += orderDetail.getProductQuantity();
                pictureList.add(orderDetail.getProductPicture());
            }
            orderThumbDTO.setQuantity(quantity);
            orderThumbDTO.setPictureList(pictureList);
            orderThumbDTOList.add(orderThumbDTO);
        }

        return new PageImpl<>(orderThumbDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    public Page<OrderDTO> findByConditions(String orderId, Integer orderType, Integer orderStatus, Date startTime, Date endTime, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByConditions(orderId, orderType, orderStatus, startTime, endTime, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    public OrderMaster updateStatus(String orderId, Integer orderStatus) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
        if(orderMaster == null) {
            throw new ShineTeaException(ResultEnum.ORDER_NOT_EXIST);
        }

        if(orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()) &&
        orderMaster.getOrderType().equals(OrderTypeEnum.SHOP_RECEIVE_ORDER.getCode()) &&
        orderStatus.equals(OrderStatusEnum.FINISH.getCode())) {
            //订单状态为0  订单类型为1（自取） 新状态orderStatus为1
            orderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        } else if(orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()) &&
                orderMaster.getOrderType().equals(OrderTypeEnum.OUTSIDE_ORDER.getCode()) &&
                orderStatus.equals(OrderStatusEnum.SHIPPING.getCode())) {
            //订单状态为0  订单类型为2（外送） 新状态orderStatus为2
            orderMaster.setOrderStatus(OrderStatusEnum.SHIPPING.getCode());
        } else if(orderMaster.getOrderStatus().equals(OrderStatusEnum.SHIPPING.getCode()) &&
                orderMaster.getOrderType().equals(OrderTypeEnum.OUTSIDE_ORDER.getCode()) &&
                orderStatus.equals(OrderStatusEnum.FINISH.getCode())) {
            //订单状态为2  订单类型为2（外送） 新状态orderStatus为1
            orderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        } else {
            throw new ShineTeaException(ResultEnum.PARAM_ERROR);
        }
        return orderMasterRepository.save(orderMaster);
    }
}
