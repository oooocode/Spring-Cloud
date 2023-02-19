package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * @Author: wth
 * @Create: 2023/2/19 - 16:00
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMainApplication7001 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMainApplication7001.class, args);
    }
}
