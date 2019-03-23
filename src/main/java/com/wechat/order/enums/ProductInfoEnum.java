package com.wechat.order.enums;

public enum ProductInfoEnum implements IEnumCode{
    UP(0,"商品在架"),
    DOWN(1,"商品已下架");

    private int code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ProductInfoEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
