package com.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author wangrui
 * @ClassName TraceDemoApplication
 * @description 数据链路Demo
 * @date 2020/1/10 10:56
 **/
@SpringBootApplication(scanBasePackages = {"com.education"})
@ServletComponentScan
public class TraceDemoApplication {

    public static void main(String[] args){
        SpringApplication.run(TraceDemoApplication.class, args);
    }

}
