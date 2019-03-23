package com.wechat.order.service.serivceImpl;

import com.wechat.order.domaim.BuyerOrderDetails;
import com.wechat.order.dto.OrderMasterDto;
import com.wechat.order.enums.BuyerOrderStatusEnums;
import com.wechat.order.enums.OrderResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class orderServiceImplTest {

    @Autowired
    private orderServiceImpl orderService;

    private String openId = "guo123456";

    private String orderId = "1543656701944119084";

    @Test
    public void create() {
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        orderMasterDto.setBuyerName("guoshuang");
        orderMasterDto.setBuyerAddress("黑龙江省哈尔滨市");
        orderMasterDto.setBuyerOpenId(openId);
        orderMasterDto.setBuyerPhone("17812787865");
        //模拟前端创建一个购物车
        List<BuyerOrderDetails> buyerOrderDetailsList = new ArrayList<BuyerOrderDetails>();

        BuyerOrderDetails buyerOrderDetails = new BuyerOrderDetails();
        buyerOrderDetails.setProductId("123456");
        buyerOrderDetails.setProductQuantity(5);

        BuyerOrderDetails buyerOrderDetails1 = new BuyerOrderDetails();
        buyerOrderDetails1.setProductId("111111");
        buyerOrderDetails1.setProductQuantity(6);

        BuyerOrderDetails buyerOrderDetails2 = new BuyerOrderDetails();
        buyerOrderDetails2.setProductId("111112");
        buyerOrderDetails2.setProductQuantity(2);

        buyerOrderDetailsList.add(buyerOrderDetails);
        buyerOrderDetailsList.add(buyerOrderDetails1);
        buyerOrderDetailsList.add(buyerOrderDetails2);
        orderMasterDto.setBuyerOrderDetailsList(buyerOrderDetailsList);
        orderService.create(orderMasterDto);

    }

    @Test
    @Transactional
    public void findOne() {
        OrderMasterDto result = orderService.findOne(orderId);
        System.out.println("订单中的所有详情个数：" + result.getBuyerOrderDetailsList().size());
        log.info("【查询单个订单：】result=:", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOrderList() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderMasterDto> orderMasterDtoPage = orderService.findOrderList(openId,pageRequest);
        System.out.println("买家订单总数量："+ orderMasterDtoPage.getTotalElements());
        Assert.assertNotEquals(0,orderMasterDtoPage.getTotalElements());
    }

    @Test
    public void cancelOrder() {
        OrderMasterDto orderMasterDto = orderService.findOne(orderId);
        System.out.println("买家下单时的订单状态："+ orderMasterDto.getBuyerOrderStatus());
        OrderMasterDto result = orderService.cancelOrder(orderMasterDto);
        System.out.println("买家取消之后的订单状态："+result.getBuyerOrderStatus());
        Assert.assertEquals(BuyerOrderStatusEnums.CANCEL.getCode(),result.getBuyerOrderStatus());
    }

    @Test
    public void finishOrder() {
        OrderMasterDto orderMasterDto = orderService.findOne(orderId);
        OrderMasterDto result = orderService.finishOrder(orderMasterDto);
        Assert.assertEquals(BuyerOrderStatusEnums.FINISH.getCode(),result.getBuyerOrderStatus());
    }

    @Test
    public void payOrder() {
        OrderMasterDto orderMasterDto = orderService.findOne(orderId);
        OrderMasterDto result = orderService.payOrder(orderMasterDto);
        Assert.assertEquals(BuyerOrderStatusEnums.FINISH.getCode(),result.getBuyerOrderPayStatus());
    }

    @Test
    public void findOrderListBySeller() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderMasterDto> orderMasterDtoPage = orderService.findOrderListBySeller(pageRequest);
        System.out.println("total:"+orderMasterDtoPage.getTotalElements());
        Assert.assertNotEquals(0,orderMasterDtoPage.getTotalElements());
    }
}