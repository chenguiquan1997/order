package com.wechat.order.service;

import com.wechat.order.domaim.ProductCategory;

import java.util.List;

public interface IProductCategoryService {
    //1.根据商品类目id查询一条数据
    public ProductCategory searchOne(Integer categoryId);
    //2.查询所有商品类目数据
    public List<ProductCategory> findAll();
    //3.根据商品的类目type查询数据
    public List<ProductCategory> findByProductCategoryType(List<Integer> type);
    //4.添加一个商品类目
    public ProductCategory insertProductCategory(ProductCategory productCategory);
}
