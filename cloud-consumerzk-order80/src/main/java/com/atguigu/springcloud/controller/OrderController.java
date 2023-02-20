package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author: wth
 * @Create: 2023/2/20 - 19:37
 */
@RestController
@RequestMapping("/consumer")
public class OrderController {

    public static final String INVOKE_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/zk")
    public String testZK() {
        return restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
    }
}
