package com.wechat.order.form;

import javax.validation.constraints.NotEmpty;

public class OrderForm {
    //买家姓名
    @NotEmpty(message = "姓名不允许为空！")
    private String name;
    //买家电话
    @NotEmpty(message = "联系电话不允许为空！")
    private String phone;
    //买家地址
    @NotEmpty(message = "地址不允许为空！")
    private String address;
    //买家微信openId
    @NotEmpty(message = "微信号不允许为空！")
    private String openid;
    //购物车详情
    @NotEmpty(message = "购物车不允许为空！")
    private String items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}
