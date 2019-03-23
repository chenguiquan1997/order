package com.wechat.order.utils;

import java.util.Random;

/**
 * 该类用于生成 orderId 和 orderDetailsId 的主键
 * 生成格式：系统当前时间+六位随机数**/
public class keyUtils {
    //当在多线程高并发的情况下，这种生成Id的方式有可能还会重复，所以要让该方法是同步的
    public static synchronized String getPrimaryKey() {
        Random randoms = new Random();
       Integer number =  randoms.nextInt(900000);
        return System.currentTimeMillis()+String.valueOf(number);
    }


}
