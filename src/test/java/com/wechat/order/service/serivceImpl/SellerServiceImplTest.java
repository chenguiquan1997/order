package com.wechat.order.service.serivceImpl;

import com.wechat.order.dao.SellerInfoRepository;
import com.wechat.order.domaim.SellerInfo;
import com.wechat.order.utils.keyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    private static final String openId = "quan669988";

    @Autowired
    private SellerServiceImpl sellerService;

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(keyUtils.getPrimaryKey());
        sellerInfo.setUsername("陈贵泉");
        sellerInfo.setPassword("123456");
        sellerInfo.setOpenId(openId);
        sellerInfoRepository.save(sellerInfo);
    }

    @Test
    public void findSellerInfoByOpenId() {
       SellerInfo sellerInfo = sellerService.findSellerInfoByOpenId(openId);
        Assert.assertEquals(openId,sellerInfo.getOpenId());
    }
}