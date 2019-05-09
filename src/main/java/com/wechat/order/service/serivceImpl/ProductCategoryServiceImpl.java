package com.wechat.order.service.serivceImpl;

import com.wechat.order.dao.ProductCategoryRepository;
import com.wechat.order.domaim.ProductCategory;
import com.wechat.order.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory searchOne(Integer categoryId) {

        return productCategoryRepository.getOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {

        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByProductCategoryType(List<Integer> type) {
        return productCategoryRepository.findByProductCategoryTypeIn(type);
    }

    @Override
    public ProductCategory insertProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
