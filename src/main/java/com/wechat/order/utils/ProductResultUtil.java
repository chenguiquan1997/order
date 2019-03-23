package com.wechat.order.utils;

import com.wechat.order.viewObject.BuyerProductVo;
import com.wechat.order.viewObject.ProductCategoryVo;

import java.util.List;

public class ProductResultUtil {

    public static BuyerProductVo ResultSuccess(List<ProductCategoryVo> productCategoryVoList) {
        BuyerProductVo buyerProductVo = new BuyerProductVo();
        buyerProductVo.setMsg("成功!");
        buyerProductVo.setCode(0);
        buyerProductVo.setData(productCategoryVoList);
        return buyerProductVo;
    }
    public static BuyerProductVo ResultSuccess(Object object) {
        BuyerProductVo buyerProductVo = new BuyerProductVo();
        buyerProductVo.setData(object);
        buyerProductVo.setCode(0);
        buyerProductVo.setMsg("成功!");
        return buyerProductVo;
    }

    public static BuyerProductVo ResultSuccess() {
        return ResultSuccess(null);
    }

    public static BuyerProductVo error(Integer code,String message) {
        BuyerProductVo buyerProductVo = new BuyerProductVo();
        buyerProductVo.setMsg(message);
        buyerProductVo.setCode(code);
        return buyerProductVo;
    }


}
