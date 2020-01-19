package com.education.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangrui
 * @ClassName IndexController
 * @description
 * @date 2020/1/17 17:18
 **/
@RestController
@RefreshScope //这边的@RefreshScope注解不能少，否则即使调用/refresh，配置也不会刷新
public class IndexController {

    @Value("${spring.cloud.config.profile}")
    private String profile;

    @RequestMapping("/profile")
    public String getProfile(){
        return profile;
    }
}
