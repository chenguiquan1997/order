package com.wechat.order.dao;

import com.wechat.order.domaim.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerOrderRepositoryTest {

    @Autowired
    private BuyerOrderRepository buyerOrderRepository;

    String openid = "weixin456";

    @Test
    public void insertTest() {
        OrderMaster buyerOrder = new OrderMaster();
        buyerOrder.setBuyerOrderId("1112");
        buyerOrder.setBuyerName("张三");
        buyerOrder.setBuyerPhone("12454364");
        buyerOrder.setBuyerAddress("北京");
        buyerOrder.setBuyerOpenId(openid);
        buyerOrder.setBuyerOrderAmount(new BigDecimal(15.6));
//        buyerOrder.setBuyerOrderPayStatus(1);
//        buyerOrder.setBuyerOrderStatus(1);
        OrderMaster buyerOrder1 = buyerOrderRepository.save(buyerOrder);
        Assert.assertNotEquals(null,buyerOrder1);
    }

    @Test
    public void findBuyerOrdersByBuyerOpenId() {
        //Pageable是一个接口，PageRequest是他的实现类
        PageRequest pageRequest = new PageRequest(1,1);
        Page<OrderMaster> result = buyerOrderRepository.findByBuyerOpenId(openid,pageRequest);
       // System.out.println(result.getTotalElements());
        Assert.assertNotEquals(0,result.getTotalElements());
    }
}