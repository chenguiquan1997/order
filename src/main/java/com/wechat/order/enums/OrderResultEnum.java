package com.wechat.order.enums;

public enum OrderResultEnum {
    PRODUCT_NOT_EXIT(0,"商品不存在!"),
    PRODUCT_QUANTITY_ERROR(-1,"商品库存不足，请重新下单!"),
    ORDERMASTER_NOT_EXIT(-2,"该商品订单不存在!"),
    ORDERDETAILS_NOT_EXIT(-3,"该订单详情不存在!"),
    ORDER_STATUS_ERROR(-4,"订单状态错误!"),
    UPDATE_ORDER_STATUS_ERROR(-5,"修改订单状态失败!"),
    STOCK_RETURN_NULL(-6,"库存返回为null !"),
    ORDER_PAY_STATUS_ERROR(-7,"订单支付状态错误!"),
    DATA_TRANSFER_ERROR(-8,"数据传输异常!"),
    ITEMS_NOTALLOW_NULL(-9,"购物车不允许为空!"),
    OPENID_ERROR(-10,"openid 错误！"),
    OPENID_ONWER_MATCHING_ERROR(-11,"该订单所属openid与买家openid不匹配!"),
    ORDER_NOT_EXIT(-12,"该订单不存在!"),
    ORDER_FINISH_ERROR(-13,"订单完结错误!"),
    ;
    private int code;

    private String message;

    OrderResultEnum(int code,String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
