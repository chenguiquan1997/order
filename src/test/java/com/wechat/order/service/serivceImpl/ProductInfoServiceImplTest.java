package com.wechat.order.service.serivceImpl;

import com.wechat.order.domaim.ProductInfo;
import com.wechat.order.enums.ProductInfoEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void searchOneById() {
        ProductInfo productInfo = productInfoService.searchOneById("123456");
        Assert.assertEquals("123456",productInfo.getProductId());
    }

    @Test
    public void searchUpAll() {
        List<ProductInfo> productInfoList = productInfoService.searchUpAll();
        System.out.println(productInfoList.size());
        for(ProductInfo p : productInfoList) {
            System.out.println(p.getProductName());
        }
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void searchAll() {
        //PageRequest继承了Pageable接口
        Pageable pageRequest = new PageRequest(0,2);
        Page<ProductInfo> page = productInfoService.searchAll(pageRequest);
        System.out.println(page.getTotalElements());
    }

    @Test
    public void insertOneInfo() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("111113");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductCategoryType(3);
        productInfo.setProductDescription("好吃美味皮皮虾！");
        productInfo.setProductIcon("https://xxx.jpg");
        productInfo.setProductStatus(ProductInfoEnum.DOWN.getCode());
        productInfo.setProductStock(100);
        productInfo.setProductPrice(new BigDecimal(6.5));
        ProductInfo productInfo1 = productInfoService.insertOneInfo(productInfo);
        Assert.assertNotNull(productInfo1);
    }

    @Test
    public void onSale() {
        String productId = "111112";
        ProductInfo productInfo = productInfoService.onSale(productId);
        Assert.assertEquals(ProductInfoEnum.UP.getCode(),productInfo.getProductStatus());
    }

    @Test
    public void offSale() {
        String productId = "111113";
        ProductInfo productInfo = productInfoService.offSale(productId);
        Assert.assertEquals(ProductInfoEnum.DOWN.getCode(),productInfo.getProductStatus());
    }
}