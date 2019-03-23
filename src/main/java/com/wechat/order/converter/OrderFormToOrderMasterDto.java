package com.wechat.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wechat.order.domaim.BuyerOrderDetails;
import com.wechat.order.dto.OrderMasterDto;
import com.wechat.order.enums.OrderResultEnum;
import com.wechat.order.exception.OrderException;
import com.wechat.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderFormToOrderMasterDto {

    public static OrderMasterDto converterToOrderMasterDto(OrderForm orderForm) {
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        orderMasterDto.setBuyerName(orderForm.getName());
        orderMasterDto.setBuyerPhone(orderForm.getPhone());
        orderMasterDto.setBuyerOpenId(orderForm.getOpenid());
        orderMasterDto.setBuyerAddress(orderForm.getAddress());
        //用于将前台购物车中的items 转换成订单主表的订单详情
        Gson gson = new Gson();
        List<BuyerOrderDetails> buyerOrderDetailsList = new ArrayList<>();
        try {
            buyerOrderDetailsList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<BuyerOrderDetails>>() {
                    }.getType());
        }catch (Exception e) {
            log.info("【数据转换异常：】items转换为gson时错误！");
            throw new OrderException(OrderResultEnum.DATA_TRANSFER_ERROR);
        }
        orderMasterDto.setBuyerOrderDetailsList(buyerOrderDetailsList);
        return orderMasterDto;
    }
}
