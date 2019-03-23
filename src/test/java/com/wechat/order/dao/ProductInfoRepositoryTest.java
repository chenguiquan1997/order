package com.wechat.order.dao;

import com.wechat.order.domaim.ProductInfo;
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
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void insertProductInfo() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("111112");
        productInfo.setProductName("馒头");
        productInfo.setProductCategoryType(3);
        productInfo.setProductDescription("好吃美味！");
        productInfo.setProductIcon("https://xxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setProductStock(100);
        productInfo.setProductPrice(new BigDecimal(3.5));
        ProductInfo productInfo1 = productInfoRepository.save(productInfo);
        Assert.assertNotNull(productInfo1);
    }

}