package com.wechat.order.controller;

import com.wechat.order.enums.WechatResultEnums;
import com.wechat.order.exception.WechatException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
//@RestController与@Controller的区别：@RestController支持RestFul风格，返回的数据类型为json格式
//不会进行重定向，如果想重定向--->需使用@Controller
//如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，
// 配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
@Controller
@Slf4j
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    //为什么要加一个回调地址参数？
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        //Mp在微信中是公众账号的意思
        log.info("进入authorize方法。。。。。。");
        /*
        * 用户同意授权以后，会找到在微信测试号网页授权模块所设置的回调域名，然后再找到该域名下的一个方法*/
        String url = "http://chenguiquan.natapp1.cc/sell/wechat/userInfo";
        /*
        构造网页授权url
        第一个参数（url）：为用户授权完成后的重定向链接
         */
       String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
       return "redirect:" + redirectUrl;

    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        //通过code码来换取access_token
        //这个code码是：当用户同意授权之后，自动回调上面设置的url,并且带回授权码
        System.out.println("this is a code:"+ code);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try{
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException wx) {
            log.info("【微信网页授权异常：】",wx);
            throw new WechatException(WechatResultEnums.WECHAT_MP_AUTHORIZE_ERROR);
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        String accessToken = wxMpOAuth2AccessToken.getAccessToken();
        log.info("openid={}",openId);
        log.info("accessToken={}",accessToken);
        return "redirect:" + returnUrl + "?openid=" + openId;
    }

}