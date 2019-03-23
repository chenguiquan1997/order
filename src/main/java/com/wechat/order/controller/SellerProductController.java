package com.wechat.order.controller;

import com.wechat.order.dao.ProductCategoryRepository;
import com.wechat.order.dao.ProductInfoRepository;
import com.wechat.order.domaim.ProductCategory;
import com.wechat.order.domaim.ProductInfo;
import com.wechat.order.enums.SellerResultEnums;
import com.wechat.order.form.ProductForm;
import com.wechat.order.service.IProductInfoService;
import com.wechat.order.utils.keyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    IProductInfoService productInfoService;

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @GetMapping("/searchAllProductBySeller")
    public ModelAndView searchAllProductBySeller(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                 @RequestParam(value = "size",defaultValue = "2") Integer size,
                                                 Map<String,Object> map) {
        PageRequest pageRequest = new PageRequest(page - 1,size);
        Page<ProductInfo> productInfoPage = productInfoService.searchAll(pageRequest);
        map.put("productInfoPageData",productInfoPage);
        map.put("currentPageData",page);
        map.put("currentSizeData",size);
        return new ModelAndView("product/productList",map);
    }

    @GetMapping("/onSale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map) {
        try{
            productInfoService.onSale(productId);
        }catch (Exception e) {
            map.put("message", SellerResultEnums.PRODUCT_CODE_ERROR.getMessage());
            map.put("url","/sell/seller/product/searchAllProductBySeller");
            log.info("【商品状态码错误：】"+SellerResultEnums.PRODUCT_CODE_ERROR.getMessage());
            return new ModelAndView("/common/error",map);
        }
        map.put("message",SellerResultEnums.PRODUCT_CODE_FIX_SUCCESS.getMessage());
        map.put("url","/sell/seller/product/searchAllProductBySeller");
        return new ModelAndView("/common/success",map);
    }

    @GetMapping("/offSale")
    public ModelAndView offSale(@RequestParam(value = "productId") String productId,
                               Map<String,Object> map) {
        try{
            productInfoService.offSale(productId);
        }catch (Exception e) {
            map.put("message", SellerResultEnums.PRODUCT_CODE_ERROR.getMessage());
            map.put("url","/sell/seller/product/searchAllProductBySeller");
            log.info("【商品状态码错误：】"+SellerResultEnums.PRODUCT_CODE_ERROR.getMessage());
            return new ModelAndView("/common/error",map);
        }
        map.put("message",SellerResultEnums.PRODUCT_CODE_FIX_SUCCESS.getMessage());
        map.put("url","/sell/seller/product/searchAllProductBySeller");
        return new ModelAndView("/common/success",map);
    }

    @GetMapping("/productUpdateOrAddUI")
    public ModelAndView productUpdateOrAddUI(@RequestParam(value = "productId",required = false) String productId,
                                            Map<String,Object> map) {
        if(!StringUtils.isEmpty(productId)) {
           ProductInfo productInfo = productInfoRepository.getOne(productId);
           map.put("productInfoData",productInfo);
        }
       List<ProductCategory> productCategoryList = productCategoryRepository.findAll();
        map.put("productCategoryListData",productCategoryList);
        map.put("url","/sell/seller/product/searchAllProductBySeller");
        return new ModelAndView("/product/productUpdateOrAdd",map);
    }

    /**
     * @Valid注解作用：使用该注释可以自动校验当前输入的参数是否符合我们的规定
     * BindingResult:如果输入的参数不合法，那么会将具体的信息添加到BindingResult中
     * 一般情况下@Valid注解和BindingResult一起使用
     */
    @PostMapping("/productUpdateOrAddByForm")
    public ModelAndView productUpdateOrAdd(@Valid ProductForm productForm,
                                            BindingResult bindingResult,
                                           Map<String,Object> map) {
        if(bindingResult.hasErrors()) {
            map.put("message",bindingResult.getFieldError().getDefaultMessage());
            log.info("【提交表单错误：】"+bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product");
            return new ModelAndView("/common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        try{
            //如果productId不为空，那么需要先从数据库查询
            if(!StringUtils.isEmpty(productForm.getProductId())) {
                productInfo = productInfoRepository.getOne(productForm.getProductId());
            }else {
                //只有新添加商品信息的情况下才需要创建productId,因为商品表id不是自增类型
                productForm.setProductId(keyUtils.getPrimaryKey());
            }
            BeanUtils.copyProperties(productForm,productInfo);
            productInfoService.insertOneInfo(productInfo);
        }catch(Exception e) {
            map.put("message",SellerResultEnums.PRODUCT_SAVE_ERROR.getMessage());
            map.put("url","/sell/seller/product/productUpdateOrAddUI");
            return new ModelAndView("/common/error",map);
        }
        map.put("message",SellerResultEnums.PRODUCT_SAVE_SUCCESS.getMessage());
        map.put("url","/sell/seller/product/searchAllProductBySeller");
        return new ModelAndView("/common/success",map);
    }
}
