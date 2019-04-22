package com.wechat.order.service.serivceImpl;

import com.wechat.order.converter.OrderMasterToOrderMasterDto;
import com.wechat.order.dao.BuyerOrderDetailsRepository;
import com.wechat.order.dao.BuyerOrderRepository;
import com.wechat.order.domaim.BuyerOrderDetails;
import com.wechat.order.domaim.OrderMaster;
import com.wechat.order.domaim.ProductInfo;
import com.wechat.order.dto.OrderMasterDto;
import com.wechat.order.dto.StockDto;
import com.wechat.order.enums.BuyerOrderPayStatusEnums;
import com.wechat.order.enums.BuyerOrderStatusEnums;
import com.wechat.order.enums.OrderResultEnum;
import com.wechat.order.exception.OrderException;
import com.wechat.order.service.IOrderService;
import com.wechat.order.service.IProductInfoService;
import com.wechat.order.utils.keyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class orderServiceImpl implements IOrderService {

    @Autowired
    BuyerOrderDetailsRepository buyerOrderDetailsRepository;

    @Autowired
    IProductInfoService iProductInfoService;

    @Autowired
    BuyerOrderRepository buyerOrderRepository;

    @Override//买家下订单
    @Transactional//该注解为事务控制注解，可以保证在数据库发生异常时，事务回滚
    public OrderMasterDto create(OrderMasterDto orderMasterDto) {
        //总价
        BigDecimal orderAmount = new BigDecimal(0);
        //orderId
        String orderId = keyUtils.getPrimaryKey();
        //创建增减库存的集合
        List<StockDto> stockDtoList = new ArrayList<StockDto>();
        //1.创建订单详情时，需要从数据库查询商品的（数量，价格）
        for (BuyerOrderDetails buyerOrderDetails : orderMasterDto.getBuyerOrderDetailsList()) {
            ProductInfo productInfo = iProductInfoService.searchOneById(buyerOrderDetails.getProductId());
            if (productInfo == null) {
                throw new OrderException(OrderResultEnum.PRODUCT_NOT_EXIT);
            }
            //2.计算订单总价格
            /**
             * 57行尤其要注意：只能在productInfo中获取商品价格，不可以在buyerOrderDetails中获取
             * 因为商品的价格时从数据库中查出来的，不是从前台传入的**/
            orderAmount = productInfo.getProductPrice()
                          .multiply(new BigDecimal(buyerOrderDetails.getProductQuantity()))
                            .add(orderAmount);
            //3.订单详情入库
            /**
             * 使用Spring框架中的对象属性复制工具类时，需要注意：
             * 他会把原先类的属性信息给覆盖掉，如果有已经被赋值的属性，那么需要复制后在设值**/
            BeanUtils.copyProperties(productInfo,buyerOrderDetails);
            buyerOrderDetails.setDetailId(keyUtils.getPrimaryKey());
            buyerOrderDetails.setOrderId(orderId);

            buyerOrderDetailsRepository.save(buyerOrderDetails);
           //创建库存详情对象
            StockDto stockDto = new StockDto(buyerOrderDetails.getProductQuantity(),buyerOrderDetails.getProductId());
            stockDtoList.add(stockDto);
        }
        //4.orderMaster入库
        OrderMaster orderMaster = new OrderMaster();
        /**
         * copyProperties 会覆盖掉订单状态和支付状态，所以要重新赋值**/
        orderMasterDto.setBuyerOrderId(orderId);
        BeanUtils.copyProperties(orderMasterDto,orderMaster);
        orderMaster.setBuyerOrderAmount(orderAmount);
        orderMaster.setBuyerOrderStatus(BuyerOrderStatusEnums.NEW.getCode());
        orderMaster.setBuyerOrderPayStatus(BuyerOrderPayStatusEnums.WAIT.getCode());
        buyerOrderRepository.save(orderMaster);
        //5.扣库存
        iProductInfoService.decreaseStock(stockDtoList);
        return orderMasterDto;
    }
    @Override//根据订单id 查询单个订单的所有详情
    //@Transactional
    public OrderMasterDto findOne(String orderId) {
        OrderMaster orderMaster = null;
        try{
             orderMaster = buyerOrderRepository.getOne(orderId);
        }catch (Exception e){
            if(orderMaster == null) {
                throw new OrderException(OrderResultEnum.ORDERMASTER_NOT_EXIT);
            }
        }
        List<BuyerOrderDetails> buyerOrderDetailsList = buyerOrderDetailsRepository.findByOrderId(orderId);
        if(buyerOrderDetailsList == null) {
            throw new OrderException(OrderResultEnum.ORDERDETAILS_NOT_EXIT);
        }
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        //将查询的订单主表信息给订单dto
        BeanUtils.copyProperties(orderMaster,orderMasterDto);
        orderMasterDto.setBuyerOrderDetailsList(buyerOrderDetailsList);
        return orderMasterDto;
    }

    @Override//根据买家openid查询所有订单
    public Page<OrderMasterDto> findOrderList(String openId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = buyerOrderRepository.findByBuyerOpenId(openId, pageable);
        //我需要返回orderMasterDto,但是从数据库查出的是orderaMaster,所以需要转换
        List<OrderMasterDto> orderMasterDtoList = OrderMasterToOrderMasterDto.conterverOrderMasterDto(orderMasterPage.getContent());
        Page<OrderMasterDto> orderMasterDtoPage = new PageImpl<OrderMasterDto>(orderMasterDtoList,pageable,orderMasterPage.getTotalElements());
        return orderMasterDtoPage;
    }

    @Override//买家取消订单
    @Transactional//****这个注解在什么情况下使用，是必须要解决的,对数据进行操作的时候使用
    public OrderMasterDto cancelOrder(OrderMasterDto orderMasterDto) {
        //1.查询订单状态
        Integer status = orderMasterDto.getBuyerOrderStatus();
        if(!status.equals(BuyerOrderStatusEnums.NEW.getCode())) {
            System.out.println("订单状态错误，无法取消!");
            throw new OrderException(OrderResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderMasterDto.setBuyerOrderStatus(BuyerOrderStatusEnums.CANCEL.getCode());
        BeanUtils.copyProperties(orderMasterDto,orderMaster);
        System.out.println("订单主表ordermaster修改后的status:"+orderMaster.getBuyerOrderStatus());
        OrderMaster orderMasterResult = buyerOrderRepository.save(orderMaster);
        if(orderMasterResult == null) {
            log.info("【修改订单状态失败!】",orderMasterResult);
            throw new OrderException(OrderResultEnum.UPDATE_ORDER_STATUS_ERROR);
        }
        //3.返回库存
       if(orderMasterDto.getBuyerOrderDetailsList() == null) {
           log.info("【订单中无商品详情】库存返回为null",orderMasterDto);
           throw new OrderException(OrderResultEnum.STOCK_RETURN_NULL);
       }
       List<StockDto> stockDtoList = new ArrayList<StockDto>();
       for(BuyerOrderDetails orderDetails : orderMasterDto.getBuyerOrderDetailsList()) {
           StockDto stockDto = new StockDto(orderDetails.getProductQuantity(),orderDetails.getProductId());
          stockDtoList.add(stockDto);
       }
        iProductInfoService.increaseStock(stockDtoList);
        //4.如果买家付款，退款给买家
        //TODO
        return orderMasterDto;
    }

    @Override//买家下单操作完成
    @Transactional
    public OrderMasterDto finishOrder(OrderMasterDto orderMasterDto) {
        //1.查看订单状态，如果订单不是新下单状态，那么将抛出异常
        Integer orderStatus = orderMasterDto.getBuyerOrderStatus();
        if( !orderStatus.equals(BuyerOrderStatusEnums.NEW.getCode())) {
            log.info("【订单状态错误：】非新下单状态" ,orderMasterDto.getBuyerOrderStatus());
            throw new OrderException(OrderResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态
        orderMasterDto.setBuyerOrderStatus(BuyerOrderStatusEnums.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDto,orderMaster);
        OrderMaster saveResult = buyerOrderRepository.save(orderMaster);
        if(saveResult == null) {
            log.info("【修改订单状态失败：】",orderMaster);
            throw new OrderException(OrderResultEnum.UPDATE_ORDER_STATUS_ERROR);
        }
        return orderMasterDto;
    }

    @Override//买家付款
    @Transactional
    public OrderMasterDto payOrder(OrderMasterDto orderMasterDto) {
        //判断订单状态，如果状态不是新下单，则抛异常
        Integer orderStatus = orderMasterDto.getBuyerOrderStatus();
        if(!orderStatus.equals(BuyerOrderStatusEnums.NEW.getCode())) {
            log.info("【该支付订单状态异常：】非新下单状态",orderStatus);
            throw new OrderException(OrderResultEnum.ORDER_STATUS_ERROR);
        }
        //判断订单支付状态，若不是未支付，则抛异常
        Integer payStatus = orderMasterDto.getBuyerOrderPayStatus();
        if(!payStatus.equals(BuyerOrderPayStatusEnums.WAIT.getCode())) {
            log.info("【该订单支付状态异常：】非未支付状态",payStatus);
            throw new OrderException(OrderResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderMasterDto.setBuyerOrderPayStatus(BuyerOrderPayStatusEnums.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDto,orderMaster);
        OrderMaster result = buyerOrderRepository.save(orderMaster);
        if(result == null) {
            log.info("【修改订单支付状态时错误：】未能正确修改");
        }
        return orderMasterDto;
    }

    @Override //卖家端，查询所有订单列表
    @Transactional
    public Page<OrderMasterDto> findOrderListBySeller(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = buyerOrderRepository.findAll(pageable);
        List<OrderMasterDto> orderMasterDtoList =  OrderMasterToOrderMasterDto.conterverOrderMasterDto(orderMasterPage.getContent());
        Page<OrderMasterDto> orderMasterDtoPage = new PageImpl<>(orderMasterDtoList,pageable,orderMasterPage.getTotalElements());
        return orderMasterDtoPage;
    }
}
