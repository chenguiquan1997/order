package com.wechat.order.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wechat.order.domaim.ProductCategory;
import com.wechat.order.enums.SellerResultEnums;
import com.wechat.order.form.CategoryForm;
import com.wechat.order.service.IProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("seller/category")
public class SellerCategoryController {

    @Autowired
    private IProductCategoryService productCategoryService;

    @GetMapping("/searchCategoryList")
    public ModelAndView searchCategoryList(Map<String,Object> map) {
       List<ProductCategory> productCategoryList = productCategoryService.findAll();
       map.put("productCategoryListData",productCategoryList);
       return new ModelAndView("category/categoryList",map);
    }

    @GetMapping("/categoryUpdateOrAddUI")
    public ModelAndView categoryUpdateOrAddUI(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                                              Map<String,Object> map) {
        ProductCategory productCategory = new ProductCategory();
        if(!(categoryId == null)) {
            try{
                productCategory = productCategoryService.searchOne(categoryId);
                map.put("productCategoryData",productCategory);
            }catch (Exception e) {
                map.put("message", SellerResultEnums.SEARCH_PRODUCT_CATEGORY_ERROR.getMessage());
                map.put("url","/sell/seller/category/searchCategoryList");
                return new ModelAndView("/common/error",map);
            }
        }
        return new ModelAndView("category/categoryUpdateOrAdd",map);
    }

    @PostMapping("/categoryUpdateOrAdd")
    public ModelAndView categoryUpdateOrAdd(@Valid CategoryForm categoryForm,
                                            BindingResult bindingResult,
                                            Map<String,Object> map) {
        if(bindingResult.hasErrors()) {
            map.put("message",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/searchCategoryList");
            return new ModelAndView("/common/error",map);
        }
        ProductCategory productCategory = new ProductCategory();
        try{
            if(categoryForm.getCategoryId() != null) {
                productCategory = productCategoryService.searchOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm,productCategory);
            productCategoryService.insertProductCategory(productCategory);
        }catch (Exception e) {
            map.put("message",SellerResultEnums.SAVE_OR_ADD_PRODUCT_CATEGORY_ERROR.getMessage());
            map.put("url","/sell/seller/category/searchCategoryList");
            return new ModelAndView("/common/error",map);
        }
        map.put("message",SellerResultEnums.SAVE_OR_ADD_PRODUCT_CATEGORY_SUCCESS.getMessage());
        map.put("url","/sell/seller/category/searchCategoryList");
        return new ModelAndView("/common/success",map);
    }

}
