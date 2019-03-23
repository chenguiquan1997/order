package com.wechat.order.dto;

public class StockDto {

    private Integer productQuantity;

    private String productId;

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public StockDto(Integer productQuantity, String productId) {
        this.productQuantity = productQuantity;
        this.productId = productId;
    }
}
