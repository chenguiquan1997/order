package com.wechat.order.form;

import lombok.Data;


@Data
public class CategoryForm {
    private Integer categoryId;
    //类目名
    private String categoryName;
    //类目编号
    private Integer productCategoryType;
}
