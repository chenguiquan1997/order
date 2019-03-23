package com.wechat.order.controller;

import com.wechat.order.converter.OrderFormToOrderMasterDto;
import com.wechat.order.dto.OrderMasterDto;
import com.wechat.order.enums.OrderResultEnum;
import com.wechat.order.exception.OrderException;
import com.wechat.order.form.OrderForm;
import com.wechat.order.service.IBuyerService;
import com.wechat.order.service.IOrderService;
import com.wechat.order.utils.DecideOpenidAndOrderidMatching;
import com.wechat.order.utils.ProductResultUtil;
import com.wechat.order.viewObject.BuyerProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/buyer/order")
public class BuyerOrderController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IBuyerService buyerService;

    //创建订单
    //@Valid 用于校验form表单的数据是否合法
    //@BindingResult 用于返回错误校验结果
    @PostMapping("/create")
    public BuyerProductVo<Map<String,String>> createOrder(@Valid OrderForm orderForm,
                                                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("【数据传输异常：】form表单数据传输异常！");
            throw new OrderException(OrderResultEnum.DATA_TRANSFER_ERROR.getCode(),
                                      bindingResult.getFieldError().getDefaultMessage());
        }
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        //orderForm需要转换成orderDto
        orderMasterDto = OrderFormToOrderMasterDto.converterToOrderMasterDto(orderForm);
        if(CollectionUtils.isEmpty(orderMasterDto.getBuyerOrderDetailsList())) {
            log.info("【购物车数据异常：】购物车不能为空!");
            throw new OrderException(OrderResultEnum.ITEMS_NOTALLOW_NULL);
        }
        OrderMasterDto result =  orderService.create(orderMasterDto);
        Map<String ,String> map = new HashMap<>();
        map.put("orderid",result.getBuyerOrderId());
        return ProductResultUtil.ResultSuccess(map);
    }
    //根据Openid查询订单列表
    @GetMapping("/list")
    public BuyerProductVo<List<OrderMasterDto>> list(@RequestParam("openid") String openid,
                                                     @RequestParam(value = "page",defaultValue = "0") Integer page,
                                                     @RequestParam(value = "size",defaultValue = "10") Integer size) {
        if(StringUtils.isEmpty(openid)) {
            log.info("【查询订单列表异常：】 openid 不存在！");
            throw new OrderException(OrderResultEnum.OPENID_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page,size);
        Page<OrderMasterDto> orderRequestList =  orderService.findOrderList(openid,pageRequest);
        return ProductResultUtil.ResultSuccess(orderRequestList.getContent());
    }

    //根据orderid查询单个订单的详情
    @GetMapping("/detail")
    public BuyerProductVo<OrderMasterDto> detail(@RequestParam("orderId") String orderId,
                                 @RequestParam("openid") String openid) {

       OrderMasterDto orderMasterDto =  buyerService.findOrderOne(openid,orderId);
        return  ProductResultUtil.ResultSuccess(orderMasterDto);
    }
    //取消订单
    @PostMapping("/cancel")
    public BuyerProductVo cancel(@RequestParam("openid") String openid,
                                 @RequestParam("orderId") String orderId) {
      OrderMasterDto orderMasterDto = buyerService.cancelOrder(openid,orderId);
       OrderMasterDto result = orderService.cancelOrder(orderMasterDto);
       return ProductResultUtil.ResultSuccess(orderMasterDto);
    }
}
