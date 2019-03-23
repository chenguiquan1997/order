package com.wechat.order.dao;

import com.wechat.order.domaim.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    //根据open_id查询卖家信息
    SellerInfo findSellerInfoByOpenId(String openId);
}
