package com.wechat.order.service.serivceImpl;

import com.wechat.order.domaim.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    ProductCategoryServiceImpl productCategoryService;

    @Test
    @Transactional
    public void searchOne() {
        ProductCategory productCategory = productCategoryService.searchOne(1);
        //添加下面这一行代码出现lazyintitalException的原因
        //懒加载的原理是什么时候用到，什么时候再加载，但是spring-hibernate-jpa执行的原理
        //是，当执行完依次操作后立即关闭session,所以当再次获取当前session中的数据时，就会抛异常
        System.out.println(productCategory.getCategoryName());
        Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    public void findByProductCategoryType() {
        List<Integer> list = Arrays.asList(111, 112, 1111);
        List<ProductCategory> productCategoryList = productCategoryService.findByProductCategoryType(list);
        for (ProductCategory p : productCategoryList) {
            System.out.println(p.getCategoryName());
            Assert.assertNotEquals(0, productCategoryList.size());
        }
    }

    @Test
    public void insertProductCategory(){
        ProductCategory productCategory = new ProductCategory("男生专享", 113);
        ProductCategory productCategory1 = productCategoryService.insertProductCategory(productCategory);
        Assert.assertEquals("男生专享", productCategory1.getCategoryName());
    }

}