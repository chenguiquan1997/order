package com.wechat.order.dao;

import com.wechat.order.domaim.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    //根据商品的状态0,1查询商品
    List<ProductInfo> findByProductStatus (Integer productStatus);
}
