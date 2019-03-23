package com.wechat.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
//@ConfigurationProperties的作用：可以将配置文件中的信息，自动封装成实体类，必须跟@Component一起使用
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    public String mpAppId;

    public String mpAppSecret;
}
