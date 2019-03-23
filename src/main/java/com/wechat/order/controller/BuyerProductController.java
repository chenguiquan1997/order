package com.wechat.order.controller;

import com.wechat.order.domaim.ProductCategory;
import com.wechat.order.domaim.ProductInfo;
import com.wechat.order.service.IProductCategoryService;
import com.wechat.order.service.IProductInfoService;
import com.wechat.order.service.serivceImpl.ProductCategoryServiceImpl;
import com.wechat.order.service.serivceImpl.ProductInfoServiceImpl;
import com.wechat.order.utils.ProductResultUtil;
import com.wechat.order.viewObject.BuyerProductVo;
import com.wechat.order.viewObject.ProductCategoryVo;
import com.wechat.order.viewObject.ProductInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

  @Autowired
   private ProductInfoServiceImpl productInfoService;

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @GetMapping("/list")
    public BuyerProductVo list() {

        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.searchUpAll();
        System.out.println("上架商品的数量："+productInfoList.size());
        //2.查询类目，一次性查询
        List<Integer> categoryTypeList = new ArrayList<>();
        for(ProductInfo p : productInfoList) {
            categoryTypeList.add(p.getProductCategoryType());
        }
//        List<Integer> categoryTypeList = productInfoList.stream()
//                .map(e -> e.getProductCategoryType())
//                .collect(Collectors.toList());
        System.out.println("类型列表数："+categoryTypeList.size());
        for(Integer i : categoryTypeList) {
            System.out.println(i);
        }
        List<ProductCategory> productCategoryList = productCategoryService.findByProductCategoryType(categoryTypeList);
        System.out.println("商品类型数量 "+productCategoryList.size());
        //3.数据拼装
        List<ProductCategoryVo> productCategoryVoList = new ArrayList<ProductCategoryVo>();
        for(ProductCategory productCategory : productCategoryList) {
            ProductCategoryVo productCategoryVo = new ProductCategoryVo();
            System.out.println(productCategory.getCategoryName());
            productCategoryVo.setCategoryType(productCategory.getProductCategoryType());
            productCategoryVo.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVo> productInfoVoList = new ArrayList<ProductInfoVo>();
            for(ProductInfo productInfo : productInfoList) {
                if(productInfo.getProductCategoryType().equals(productCategory.getProductCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    //如果要将productInfo中的字段信息 复制到 productInfoVo中的相应字段中，两个类中的字段名必须相同
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productCategoryVo.setProductInfoVoList(productInfoVoList);
            productCategoryVoList.add(productCategoryVo);
        }
        System.out.println(productCategoryVoList);
      return ProductResultUtil.ResultSuccess(productCategoryVoList);
    }
}
