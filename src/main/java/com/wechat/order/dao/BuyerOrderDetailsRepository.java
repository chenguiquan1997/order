package com.wechat.order.dao;

import com.wechat.order.domaim.BuyerOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyerOrderDetailsRepository extends JpaRepository<BuyerOrderDetails,String> {
    /**很坑：
     * 注入的参数名 必须与实体类中的 变量名 一致,方法名也不能乱写，要与实体类中的属性相对应**/
    //根据订单id查询商品明细
    List<BuyerOrderDetails> findByOrderId(String orderId);
}
