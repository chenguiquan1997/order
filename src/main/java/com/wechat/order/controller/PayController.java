package com.wechat.order.controller;

import com.wechat.order.dto.OrderMasterDto;
import com.wechat.order.enums.OrderResultEnum;
import com.wechat.order.exception.OrderException;
import com.wechat.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/create")
    public void createPay(@RequestParam("orderId") String orderId,
                            @RequestParam("returnUrl") String returnUrl) {
      OrderMasterDto orderMasterDto = orderService.findOne(orderId);
      if(orderMasterDto == null) {
          throw new OrderException(OrderResultEnum.ORDER_NOT_EXIT);
      }
    }
}
