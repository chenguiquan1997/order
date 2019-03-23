package com.wechat.order.viewObject;
/*
* http请求返回的最外层对象*/
public class BuyerProductVo<T> {

    private int code;

    private String msg;
    //买家商品数据信息
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
