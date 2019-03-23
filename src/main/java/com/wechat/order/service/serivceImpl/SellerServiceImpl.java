package com.wechat.order.service.serivceImpl;

import com.wechat.order.dao.SellerInfoRepository;
import com.wechat.order.domaim.SellerInfo;
import com.wechat.order.service.ISellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements ISellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenId(String openId) {
       SellerInfo sellerInfo = sellerInfoRepository.findSellerInfoByOpenId(openId);
        return sellerInfo;
    }
}
