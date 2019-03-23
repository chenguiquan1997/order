package com.wechat.order.exception;

import com.wechat.order.enums.SellerResultEnums;

public class SellerException extends RuntimeException {

    private int code;

    public SellerException(SellerResultEnums sellerResultEnums) {
        super(sellerResultEnums.getMessage());
        this.code = sellerResultEnums.getCode();
    }

}
