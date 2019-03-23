package com.wechat.order.converter;

import com.wechat.order.domaim.OrderMaster;
import com.wechat.order.dto.OrderMasterDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMasterToOrderMasterDto {
    public static OrderMasterDto converterOrderMasterDto(OrderMaster orderMaster) {
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        BeanUtils.copyProperties(orderMaster,orderMasterDto);
        return orderMasterDto;
    }

    public static List<OrderMasterDto> conterverOrderMasterDto(List<OrderMaster> orderMasterList) {
//        return orderMasterList.stream()
//                .map(e -> converterOrderMasterDto(e)).collect(Collectors.toList());
        List<OrderMasterDto> orderMasterDtoList = new ArrayList<>();
        for(OrderMaster orderMaster : orderMasterList) {
           OrderMasterDto orderMasterDto = new OrderMasterDto();
            BeanUtils.copyProperties(orderMaster,orderMasterDto);
            orderMasterDtoList.add(orderMasterDto);
        }
        return orderMasterDtoList;
    }


}
