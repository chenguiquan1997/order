package com.wechat.order.controller;

import com.lly835.bestpay.model.PayResponse;
import com.wechat.order.dto.OrderMasterDto;
import com.wechat.order.enums.OrderResultEnum;
import com.wechat.order.exception.OrderException;
import com.wechat.order.service.IOrderService;
import com.wechat.order.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IPayService payService;

    @GetMapping("/create")
    public ModelAndView createPay(@RequestParam("orderId") String orderId,
                                  @RequestParam("returnUrl") String returnUrl,
                                  Map<String,Object> map) {
        /*
        * 首先先查询订单，再从订单里面查找金额*/
      OrderMasterDto orderMasterDto = orderService.findOne(orderId);
      if(orderMasterDto == null) {
          throw new OrderException(OrderResultEnum.ORDER_NOT_EXIT);
      }
      //发起支付
      PayResponse payResponse = payService.createPay(orderMasterDto);
      map.put("payResponse",payResponse);
      map.put("returnUrl",returnUrl);
      return new ModelAndView("/pay/create",map);
    }

    /**
     * 微信异步通知
     * @param notifyData
     * @return
     */
    @PostMapping("/asyWechatNotify")
    public ModelAndView asyWechatNotify(@RequestBody String notifyData) {
        payService.wxNotify(notifyData);
        //返回给微信处理结果
        return new ModelAndView("/pay/success");
    }
}
