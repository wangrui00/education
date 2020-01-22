package com.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangrui
 * @ClassName FeignApplication
 * @description OpenFeign
 * @date 2020/1/21 16:19
 **/
/** 开启 Feign 扫描支持 */
@SpringBootApplication(scanBasePackages  = {"com.education"})
@EnableFeignClients
public class FeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
        System.out.println("Feign 服务启动成功!");
    }
}
