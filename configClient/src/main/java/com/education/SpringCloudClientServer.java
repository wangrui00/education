package com.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangrui
 * @ClassName SpringCloudClientServer
 * @description SpringCloudClient配置
 * @date 2020/1/17 17:16
 **/
@SpringBootApplication
public class SpringCloudClientServer {

    public static void main(String[] args){
        SpringApplication.run(SpringCloudClientServer.class, args);
        System.out.println("配置中心客户端启动成功!");
    }
}
