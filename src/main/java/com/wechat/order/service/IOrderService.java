package com.wechat.order.service;

import com.wechat.order.dto.OrderMasterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    //创建订单
    public OrderMasterDto create(OrderMasterDto orderMasterDto);
    //查询单个订单
    public OrderMasterDto findOne(String orderId);
    //根据openid查询订单列表
    public Page<OrderMasterDto> findOrderList(String openId, Pageable pageable);
    //取消订单
    public OrderMasterDto cancelOrder(OrderMasterDto orderMasterDto);
    //完结订单
    public OrderMasterDto finishOrder(OrderMasterDto orderMasterDto);
    //支付订单
    public OrderMasterDto payOrder(OrderMasterDto orderMasterDto);
    //卖家端，查询所有订单列表
    public Page<OrderMasterDto> findOrderListBySeller(Pageable pageable);
}
