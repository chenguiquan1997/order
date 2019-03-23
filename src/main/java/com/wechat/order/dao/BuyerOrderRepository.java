package com.wechat.order.dao;

import com.wechat.order.domaim.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerOrderRepository extends JpaRepository<OrderMaster,String> {
    //根据买家openId查询订单列表
    Page<OrderMaster> findByBuyerOpenId(String openId, Pageable pageable);
}
