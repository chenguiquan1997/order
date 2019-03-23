package com.wechat.order.controller;

import com.wechat.order.dto.OrderMasterDto;
import com.wechat.order.enums.OrderResultEnum;
import com.wechat.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/seller")
public class SellerOrderController {

    @Autowired
    IOrderService orderService;
    //在使用templates模板时，必须要引入freemarker Maven依赖
    @GetMapping("/findOrderListBySeller")
    public ModelAndView findOrderListBySeller(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                            @RequestParam(value = "size",defaultValue = "5") Integer size,
                                            Map<String,Object> map) {
        System.out.println("进入方法");
        PageRequest pageRequest = new PageRequest(page - 1,size);
        Page<OrderMasterDto> orderMasterDtoPage = orderService.findOrderListBySeller(pageRequest);
        System.out.println("total "+orderMasterDtoPage.getTotalPages());
        orderMasterDtoPage.getContent();
        map.put("orderMasterDtoPageData",orderMasterDtoPage);
        map.put("currentPageData",page);
        map.put("currentSizeData",size);
        return new ModelAndView("order/list",map);
    }

    @GetMapping("/cancelOrder")//卖家取消订单
    public ModelAndView cancelOrder(@RequestParam("orderId") String orderId,
                                     Map<String,Object> map) {
        //如果在service的方法中出现异常，
        // 在controller中可以使用try/catch进行捕获，在controller中统一处理
        try{
            OrderMasterDto orderMasterDto = orderService.findOne(orderId);
            orderService.cancelOrder(orderMasterDto);
        }catch (Exception e) {
            map.put("url","findOrderListBySeller");
            map.put("message",OrderResultEnum.ORDER_NOT_EXIT.getMessage());
            log.info("【订单不存在：】"+ OrderResultEnum.ORDER_NOT_EXIT);
            return new ModelAndView("common/error",map);
        }
        map.put("url","findOrderListBySeller");

       return new ModelAndView("order/cancel",map);
    }

    @GetMapping("/searchOrderDetailsBySeller")
    public ModelAndView searchOrderDetailsBySeller(@RequestParam("orderId") String orderId,
                                                   Map<String,Object> map) {
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        try{
            orderMasterDto = orderService.findOne(orderId);
        }catch (Exception e) {
            log.info("【订单不存在：】"+ OrderResultEnum.ORDER_NOT_EXIT);
            map.put("message",OrderResultEnum.ORDER_NOT_EXIT.getMessage());
            return new ModelAndView("common/error",map);
        }
        map.put("url","findOrderListBySeller");
        map.put("orderMasterDtoData",orderMasterDto);
        return new ModelAndView("order/details",map);
    }
    @GetMapping("/finishOrderBySeller")
    public ModelAndView finishOrderBySeller(@RequestParam("orderId") String orderId,
                                            Map<String,Object> map) {
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        try{
            orderMasterDto = orderService.findOne(orderId);
            orderService.finishOrder(orderMasterDto);
        }catch (Exception e) {
            log.info("【订单完结操作异常：】" + OrderResultEnum.ORDER_FINISH_ERROR);
            map.put("message",OrderResultEnum.ORDER_FINISH_ERROR.getMessage());
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/findOrderListBySeller");
        map.put("message","订单完结成功!");
        return new ModelAndView("common/success",map);
    }

}
