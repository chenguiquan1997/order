package com.wechat.order.dao;

import com.wechat.order.domaim.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//dao层的接口继承jpa仓库，里面需要声明继承哪个类，和该类的主键
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    //findByProductCategoryTypeIn为固定写法，需要根据语法要求
    List<ProductCategory> findByProductCategoryTypeIn(List<Integer> productCategoryList);
}
