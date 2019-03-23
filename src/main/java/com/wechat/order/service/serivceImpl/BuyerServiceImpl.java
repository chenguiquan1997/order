package com.wechat.order.service.serivceImpl;

import com.wechat.order.dto.OrderMasterDto;
import com.wechat.order.enums.OrderResultEnum;
import com.wechat.order.exception.OrderException;
import com.wechat.order.service.IBuyerService;
import com.wechat.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements IBuyerService {

    @Autowired
    private IOrderService orderService;
    @Override
    public OrderMasterDto findOrderOne(String openid, String orderId) {
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

    @Override
    public OrderMasterDto cancelOrder(String openid, String orderId) {
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
