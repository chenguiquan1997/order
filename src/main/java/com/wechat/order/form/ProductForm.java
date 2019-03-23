package com.wechat.order.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductForm {
    //商品Id
    private String productId;
    //商品名
    private String productName;
    //商品价格
    private BigDecimal productPrice;
    //商品库存
    private Integer productStock;
    //商品描述
    private String productDescription;
    //商品图片
    private String productIcon;
    //商品类目编号
    private Integer productCategoryType;
}
