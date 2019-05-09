package com.wechat.order.service.serivceImpl;

import com.wechat.order.dao.ProductInfoRepository;
import com.wechat.order.domaim.ProductInfo;
import com.wechat.order.dto.StockDto;
import com.wechat.order.enums.OrderResultEnum;
import com.wechat.order.enums.ProductInfoEnum;
import com.wechat.order.enums.SellerResultEnums;
import com.wechat.order.exception.OrderException;
import com.wechat.order.exception.SellerException;
import com.wechat.order.service.IProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class ProductInfoServiceImpl implements IProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public ProductInfo searchOneById(String productInfoId) {

        return productInfoRepository.getOne(productInfoId);
    }

    @Override
    public List<ProductInfo> searchUpAll() {
        return productInfoRepository.findByProductStatus(ProductInfoEnum.UP.getCode());
    }

    @Override//当传入分页参数的时候，该方法返回的是一个Page对象.查询所有商品信息
    public Page<ProductInfo> searchAll(Pageable pageable) {

        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo insertOneInfo(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override//在买家取消订单以后,需返回商品库存
    @Transactional
    public void increaseStock(List<StockDto> stockDtoList) {
        for(StockDto stockDto : stockDtoList) {
            ProductInfo productInfo = productInfoRepository.getOne(stockDto.getProductId());
            if(productInfo == null) {
                System.out.println("该商品不存在！");
                throw new OrderException(OrderResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer quantity = productInfo.getProductStock() + stockDto.getProductQuantity();
            productInfo.setProductStock(quantity);
            productInfoRepository.save(productInfo);
        }
    }

    @Override//扣商品库存
    @Transactional
    public void decreaseStock(List<StockDto> stockDtoList) {
        for(StockDto list : stockDtoList) {
            ProductInfo productInfo = productInfoRepository.getOne(list.getProductId());
            if(productInfo == null) {
                throw new OrderException(OrderResultEnum.PRODUCT_NOT_EXIT);
            }else {
                Integer productQuantity = productInfo.getProductStock() - list.getProductQuantity();
                if(productQuantity < 0) {
                    throw new OrderException(OrderResultEnum.PRODUCT_QUANTITY_ERROR);
                }
                productInfo.setProductStock(productQuantity);
                productInfoRepository.save(productInfo);
            }

        }
    }

    @Override//卖家商品上架
    @Transactional
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoRepository.getOne(productId);
        if(productInfo == null) {
            throw new SellerException(SellerResultEnums.PRODUCT_NOT_EXIT);
        }
        if(productInfo.getProductStatus() == ProductInfoEnum.UP.getCode()) {
            throw new SellerException(SellerResultEnums.PRODUCT_CODE_ERROR);
        }
        productInfo.setProductStatus(ProductInfoEnum.UP.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Override//卖家商品下架
    @Transactional
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoRepository.getOne(productId);
        if(productInfo == null) {
            throw new SellerException(SellerResultEnums.PRODUCT_NOT_EXIT);
        }
        if(productInfo.getProductStatus() == ProductInfoEnum.DOWN.getCode()) {
            throw new SellerException(SellerResultEnums.PRODUCT_CODE_ERROR);
        }
        productInfo.setProductStatus(ProductInfoEnum.DOWN.getCode());
        return productInfoRepository.save(productInfo);
    }
}
