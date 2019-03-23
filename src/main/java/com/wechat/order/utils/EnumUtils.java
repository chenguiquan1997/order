package com.wechat.order.utils;

import com.wechat.order.enums.IEnumCode;

public class EnumUtils {
    //<T extends IEnumCode>表示的是 ： 继承了IEnumCode的一个实现类，不是继承了接口
    //只有接口才能继承接口，并且还可以多继承
    public static <T extends IEnumCode> T getByCode(Integer code,Class<T> enmuClass) {
        for(T each : enmuClass.getEnumConstants()) {
            if(each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }


}
