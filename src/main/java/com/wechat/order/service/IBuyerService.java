package com.wechat.order.service;

import com.wechat.order.dto.OrderMasterDto;

public interface IBuyerService {

    public OrderMasterDto findOrderOne(String openid, String orderId);

    public OrderMasterDto cancelOrder(String openid,String orderId);
}
