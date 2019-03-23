package com.wechat.order.domaim;

import com.wechat.order.enums.BuyerOrderPayStatusEnums;
import com.wechat.order.enums.ProductInfoEnum;
import com.wechat.order.utils.EnumUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class ProductInfo {

    @Id//商品Id
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
    //商品状态：0-正常，1-下架
    private Integer productStatus = ProductInfoEnum.UP.getCode();
    //创建时间
    private Date productCreateTime;
    //修改时间
    private Date productUpdateTime;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public Integer getProductCategoryType() {
        return productCategoryType;
    }

    public void setProductCategoryType(Integer productCategoryType) {
        this.productCategoryType = productCategoryType;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Date getProductCreateTime() {
        return productCreateTime;
    }

    public void setProductCreateTime(Date productCreateTime) {
        this.productCreateTime = productCreateTime;
    }

    public Date getProductUpdateTime() {
        return productUpdateTime;
    }

    public void setProductUpdateTime(Date productUpdateTime) {
        this.productUpdateTime = productUpdateTime;
    }

    public ProductInfoEnum getProductStatusCode() {
        return EnumUtils.getByCode(productStatus, ProductInfoEnum.class);
    }
}
