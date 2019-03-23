package com.wechat.order.enums;

public enum WechatResultEnums {
    WECHAT_MP_AUTHORIZE_ERROR(-1,"微信网页授权异常!"),
        ;

    private Integer code;

    private String message;

    WechatResultEnums(Integer code,String message) {
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
