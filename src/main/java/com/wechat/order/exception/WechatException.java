package com.wechat.order.exception;

import com.wechat.order.enums.WechatResultEnums;
//自定义微信异常
public class WechatException extends RuntimeException {

    public WechatException(WechatResultEnums wechatResultEnums) {
        super(wechatResultEnums.getMessage());
    }
}
