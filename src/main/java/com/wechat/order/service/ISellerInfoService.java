package com.wechat.order.service;

import com.wechat.order.domaim.SellerInfo;

public interface ISellerInfoService {
    SellerInfo findSellerInfoByOpenId(String openId);
}
