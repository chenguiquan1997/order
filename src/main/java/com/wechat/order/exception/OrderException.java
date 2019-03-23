package com.wechat.order.exception;

import com.wechat.order.enums.OrderResultEnum;
//自定义订单异常
public class OrderException extends RuntimeException {

    private int code;

    public OrderException(OrderResultEnum orderResultEnum) {
        //因为它们的顶级父类exception中有一个exception(String message)
        //的构造方法
        super(orderResultEnum.getMessage());
        this.code = orderResultEnum.getCode();
    }

    public OrderException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
