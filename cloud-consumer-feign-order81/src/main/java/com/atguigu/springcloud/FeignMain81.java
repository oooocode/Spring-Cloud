package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: wth
 * @Create: ${DATE} - ${TIME}
 */
@SpringBootApplication
@EnableFeignClients
public class FeignMain81 {
    public static void main(String[] args) {
        SpringApplication.run(FeignMain81.class, args);
    }
}