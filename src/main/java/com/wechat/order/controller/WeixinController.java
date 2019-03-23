package com.wechat.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@Slf4j
@RequestMapping("/weixin")
public class WeixinController {
    private static final String token = "tokensh";

    @RequestMapping("/auth")
    public String auth(HttpServletRequest request, HttpServletResponse response,@RequestParam("code") String code) throws IOException {
       // @RequestParam("code") String code
        String signature = request.getParameter("singnature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println("signature:" + signature);
        System.out.println("timestamp:" + timestamp);
        System.out.println("nonce:" + nonce);
        System.out.println("echostr:" + echostr);
        log.info("进入auth方法!");
       System.out.println(code);

       String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxf53427a5c2e364c5&secret=7f082f7331d07e01440ce90bea859cdd&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String reponses = restTemplate.getForObject(url,String.class);
        System.out.println("asscess_token:"+reponses);
        return echostr;


       //log.info("code码",code);
       // return "hello";
    }
}
