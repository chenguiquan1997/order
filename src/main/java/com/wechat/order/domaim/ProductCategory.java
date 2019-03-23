package com.wechat.order.domaim;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@DynamicUpdate//让时间字段自动更新
@Data//使用该注解可以不需要写get和set方法，它以来的时lombok插件
public class ProductCategory {
    @Id
    //如果执行插入操作时出现error performing isolated work 错误，
    //需要在@GeneratedValue中加入strategy = GenerationType.IDENTITY
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增类型，商品类目id
    private Integer categoryId;
    //类目名
    private String categoryName;
    //类目编号
    private Integer productCategoryType;
    //类目创建时间
    private Date categoryCreateTime;
    //类目修改时间
    private  Date categoryUpdateTime;

    public ProductCategory() {}

    public ProductCategory(String categoryName,Integer productCategoryType) {
        this.categoryName = categoryName;
        this.productCategoryType = productCategoryType;
    }


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getProductCategoryType() {
        return productCategoryType;
    }

    public void setProductCategoryType(Integer productCategoryType) {
        this.productCategoryType = productCategoryType;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", productCategoryType=" + productCategoryType +
                '}';
    }
}
