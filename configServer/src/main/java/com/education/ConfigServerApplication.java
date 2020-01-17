package com.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author wangrui
 * @ClassName ConfigServerApplication
 * @description 配置中心服务的主类
 * @date 2020/1/15 15:09
 **/
@SpringBootApplication
@EnableConfigServer // 通过@EnableConfigServer注解激活配置服务
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
        System.out.println("配置中心服务端启动成功!");
    }
}
