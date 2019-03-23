package com.wechat.order.domaim;

import com.wechat.order.enums.BuyerOrderPayStatusEnums;
import com.wechat.order.enums.BuyerOrderStatusEnums;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate//让订单时间自动更新
public class OrderMaster {
    /**
     * 很坑：使用hibernate框架时，数据库字段与实体类的对应准则：
     * 实体类中的大写字母，相当于数据库字段的分割线“_”
     * 实体类的变量名 必须与数据库中的字段一致，才能自动注入成功**/
    //买家订单id
    @Id
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
    private Integer buyerOrderStatus = BuyerOrderStatusEnums.NEW.getCode();
    //订单支付状态，默认0为未支付
    @Column(name="pay_status")
    private Integer buyerOrderPayStatus = BuyerOrderPayStatusEnums.WAIT.getCode();
    //订单创建时间
    @Column(name="order_create_time")
    private Date BuyerOrderCreateTime;
    //订单修改时间
    @Column(name="order_update_time")
    private Date BuyerOrderUpdateTime;


    public Integer getBuyerOrderPayStatus() {
        return buyerOrderPayStatus;
    }

    public void setBuyerOrderPayStatus(Integer buyerOrderPayStatus) {
        this.buyerOrderPayStatus = buyerOrderPayStatus;
    }

    public String getBuyerOrderId() {
        return buyerOrderId;
    }

    public void setBuyerOrderId(String buyerOrderId) {
        this.buyerOrderId = buyerOrderId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerOpenId() {
        return buyerOpenId;
    }

    public void setBuyerOpenId(String buyerOpenId) {
        this.buyerOpenId = buyerOpenId;
    }

    public BigDecimal getBuyerOrderAmount() {
        return buyerOrderAmount;
    }

    public void setBuyerOrderAmount(BigDecimal buyerOrderAmount) {
        this.buyerOrderAmount = buyerOrderAmount;
    }

    public Integer getBuyerOrderStatus() {
        return buyerOrderStatus;
    }

    public void setBuyerOrderStatus(Integer buyerOrderStatus) {
        this.buyerOrderStatus = buyerOrderStatus;
    }

    public Date getBuyerOrderCreateTime() {
        return BuyerOrderCreateTime;
    }

    public void setBuyerOrderCreateTime(Date buyerOrderCreateTime) {
        BuyerOrderCreateTime = buyerOrderCreateTime;
    }

    public Date getBuyerOrderUpdateTime() {
        return BuyerOrderUpdateTime;
    }

    public void setBuyerOrderUpdateTime(Date buyerOrderUpdateTime) {
        BuyerOrderUpdateTime = buyerOrderUpdateTime;
    }
}
