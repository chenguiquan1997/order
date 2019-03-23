package com.wechat.order.dao;

import com.wechat.order.domaim.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    //在spring boot 集成jpa时出现could not initialize prox错误时
    //在测试方法上面加@Transactional，即添加事务控制注解，保证了该操作过程是一个会话过程
    //出错原因：单元在进行数据库访问的时候，针对数据库的访问与操作session已经释放了
    @Transactional
    public void findOneTest() {
        ProductCategory pc = repository.getOne(1);
       // ProductCategory productCategory = repository.findOne(new Integer(1));
        System.out.println(pc.toString());
    }

    @Test
    public void insertOneTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("zhangsan");
        productCategory.setProductCategoryType(111);
        repository.save(productCategory);
    }

    @Test
    public void updateOneTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("lisi");
        productCategory.setProductCategoryType(112);
        repository.save(productCategory);
    }


    @Transactional
    @Test
    public void updateOneByAuthorityTest() {
        ProductCategory productCategory = repository.getOne(2);
        System.out.println(productCategory.getCategoryId());
        productCategory.setCategoryId(productCategory.getCategoryId());
        productCategory.setProductCategoryType(000);
        productCategory.setCategoryName("chen");
        repository.save(productCategory);
    }

    @Test
    public void findByProductCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(1111,111,112);
        List<ProductCategory> productCategoryList = repository.findByProductCategoryTypeIn(list);
        Assert.assertNotEquals(0,productCategoryList.size());
        System.out.println(productCategoryList.size());
    }



}