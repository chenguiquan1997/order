package com.wechat.order.enums;

public enum BuyerOrderPayStatusEnums implements IEnumCode{
    WAIT(0,"未支付"),
    SUCCESS(1,"已支付");

    private Integer code;

    private String message;

    BuyerOrderPayStatusEnums(Integer code,String message) {
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
