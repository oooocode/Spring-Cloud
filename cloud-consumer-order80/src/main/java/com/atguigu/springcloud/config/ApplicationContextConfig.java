package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: wth
 * @Create: 2023/2/19 - 11:19
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced // 开启eureka负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
