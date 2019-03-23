package com.wechat.order.utils;

import com.wechat.order.dto.OrderMasterDto;
import com.wechat.order.enums.OrderResultEnum;
import com.wechat.order.exception.OrderException;
import com.wechat.order.service.IOrderService;
import com.wechat.order.service.serivceImpl.orderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
@Slf4j
//判断当前的orderId是否是当前openId名下的
public class DecideOpenidAndOrderidMatching {
    @Autowired
    private IOrderService orderService;

    //查询订单时判断
    public  OrderMasterDto findOrderOne(String openid,String orderId) {
        OrderMasterDto orderMasterDto = orderService.findOne(orderId);
        if(orderMasterDto == null) {
            return null;
        }
        if(!orderMasterDto.getBuyerOpenId().equalsIgnoreCase(openid)) {
                log.info("【订单查询异常：】该订单openid与买家openid不匹配!");
                throw new OrderException(OrderResultEnum.OPENID_ONWER_MATCHING_ERROR);
        }
        return orderMasterDto;
    }

    //取消订单时判断
    public OrderMasterDto cancelOrder(String openid,String orderId) {
        IOrderService orderService = new orderServiceImpl();
        OrderMasterDto orderMasterDto = orderService.findOne(orderId);
        if(orderMasterDto == null) {
            return null;
        }
        if(!orderMasterDto.getBuyerOpenId().equalsIgnoreCase(openid)) {
            log.info("【订单取消异常：】该订单openid与买家openid不匹配!");
            throw new OrderException(OrderResultEnum.OPENID_ONWER_MATCHING_ERROR);
        }
        return orderMasterDto;
    }
}
