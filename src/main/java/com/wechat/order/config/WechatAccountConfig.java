package com.wechat.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
//@ConfigurationProperties的作用：可以将配置文件中的信息，自动封装成实体类，必须跟@Component一起使用
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    /*
    * 微信公众号id*/
    public String mpAppId;
    /**
     * 微信公众号密匙
     */
    public String mpAppSecret;

    /*
    * 商户号*/
    private String mchId;

    /*
    * 商户密匙*/
    private String mchKey;

    /*
    * 商户密匙路径*/
    private String keyPath;

    /*
    * 微信支付异步通知地址*/
    private String notifyUrl;
}
