package com.wechat.order.dto;
//数据传输对象 DataTransferObject

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wechat.order.domaim.BuyerOrderDetails;
import com.wechat.order.enums.BuyerOrderPayStatusEnums;
import com.wechat.order.enums.BuyerOrderStatusEnums;
import com.wechat.order.serializer.DateToLongSerializer;
import com.wechat.order.utils.EnumUtils;
import lombok.Data;


import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//该注释的意思是如果返回给前端的数据是空，那么就不返回
//如果该字段必须返回给前端，那么就给该字段赋一个初始值
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderMasterDto {

    @Column(name="order_id")
    private String buyerOrderId;
    //买家姓名
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家微信id
    @Column(name="buyer_openid")
    private String buyerOpenId;
    //买家订单总金额
    @Column(name="order_amount")
    private BigDecimal buyerOrderAmount;
    //买家订单状态,默认为新下单
    @Column(name="order_status")
    private Integer buyerOrderStatus;
    //订单支付状态，默认0为未支付
    @Column(name="pay_status")
    private Integer buyerOrderPayStatus;
    //订单创建时间
    @JsonSerialize(using = DateToLongSerializer.class)
    @Column(name="order_create_time")
    private Date BuyerOrderCreateTime;
    //订单修改时间
    @JsonSerialize(using = DateToLongSerializer.class)
    @Column(name="order_update_time")
    private Date BuyerOrderUpdateTime;
    //根据openid返回多条订单明细
    List<BuyerOrderDetails> buyerOrderDetailsList;

    //通过订单code得到订单状态
    @JsonIgnore//作用：转化成json格式时，自动忽略
    public BuyerOrderStatusEnums getOrderStatusByCode() {
        return EnumUtils.getByCode(buyerOrderStatus,BuyerOrderStatusEnums.class);
    }

    //通过支付code得到支付状态
    @JsonIgnore
    public BuyerOrderPayStatusEnums getOrderPayStatusByCode() {
        return EnumUtils.getByCode(buyerOrderPayStatus,BuyerOrderPayStatusEnums.class);
    }

}
