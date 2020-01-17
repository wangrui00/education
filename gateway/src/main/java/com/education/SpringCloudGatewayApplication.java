package com.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangrui
 * @ClassName SpringCloudGatewayApplication
 * @description 网关应用
 * @date 2020/1/14 11:30
 **/
@RestController
@SpringBootApplication(scanBasePackages  = {"com.education"})
public class SpringCloudGatewayApplication {

    public static void main(String[] args){
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }
}
