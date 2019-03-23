package com.wechat.order.enums;

public enum SellerResultEnums {
    PRODUCT_NOT_EXIT(-1,"商品不存在！"),
    PRODUCT_CODE_ERROR(-2,"商品状态码错误,无法修改上/下架状态!"),
    PRODUCT_CODE_FIX_SUCCESS(-3,"商品上下架状态修改成功!"),
    PRODUCT_SAVE_ERROR(-4,"添加商品信息失败!"),
    PRODUCT_SAVE_SUCCESS(-5,"成功添加商品信息!"),
    SEARCH_PRODUCT_CATEGORY_ERROR(-6,"查询商品类目错误!"),
    SAVE_OR_ADD_PRODUCT_CATEGORY_ERROR(-7,"修改或添加商品类目错误!"),
    SAVE_OR_ADD_PRODUCT_CATEGORY_SUCCESS(-8,"添加或修改商品类目成功!"),
    ;
    private int code;

    private String message;

    //枚举中的构造方法不需要加public权限修饰符，否则报错
    SellerResultEnums(int code,String message) {
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
