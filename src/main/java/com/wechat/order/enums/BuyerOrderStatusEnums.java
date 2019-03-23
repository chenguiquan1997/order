package com.wechat.order.enums;

public enum BuyerOrderStatusEnums implements IEnumCode{
    NEW(0,"新订单"),
    FINISH(1,"订单已完结"),
    CANCEL(2,"订单已取消");

    private Integer code;

    private String message;

    BuyerOrderStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
