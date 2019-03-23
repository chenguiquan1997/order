package com.wechat.order.dao;

import com.wechat.order.domaim.BuyerOrderDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerOrderDetailsRepositoryTest {

    @Autowired
    BuyerOrderDetailsRepository buyerOrderDetailsRepository;

    @Test
    public void insertTest() {
        BuyerOrderDetails buyerOrderDetails = new BuyerOrderDetails();
        buyerOrderDetails.setDetailId("11111");
        buyerOrderDetails.setOrderId("1111");
        buyerOrderDetails.setProductId("111111");
        buyerOrderDetails.setProductName("馒头");
        buyerOrderDetails.setProductIcon("http://xxxx");
        buyerOrderDetails.setProductPrice(new BigDecimal(12.3));
        buyerOrderDetails.setProductQuantity(12);
        BuyerOrderDetails result = buyerOrderDetailsRepository.save(buyerOrderDetails);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<BuyerOrderDetails> buyerOrderDetailsList = buyerOrderDetailsRepository.findByOrderId("1111");
        Assert.assertNotNull(buyerOrderDetailsList);
    }
}