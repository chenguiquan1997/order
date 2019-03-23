package com.wechat.order.service;

import com.wechat.order.domaim.ProductInfo;
import com.wechat.order.dto.StockDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IProductInfoService {
    //1.根据商品id查询
    public ProductInfo searchOneById(String productInfoId);
    //2.查询所有上架商品
    public List<ProductInfo> searchUpAll();
    //3.查询所有商品，并且进行分页显示
    public Page<ProductInfo> searchAll(Pageable pageable);
    //4.增加一条商品信息
    public ProductInfo insertOneInfo(ProductInfo productInfo);
    //5.增加库存
    public void increaseStock(List<StockDto> stockDtoList);
    //6.消减库存
    public void decreaseStock(List<StockDto> stockDtoList);
    //7.卖家商品上架
    public ProductInfo onSale(String productId);
    //8.卖家商品下架
    public ProductInfo offSale(String productId);
}
