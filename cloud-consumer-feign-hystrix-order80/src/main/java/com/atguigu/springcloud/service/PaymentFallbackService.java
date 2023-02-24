package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author: wth
 * @Create: 2023/2/24 - 11:26
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfoOk(Integer id) {
        return "服务调用失败，paymentInfoOk 提示来自：cloud-consumer-feign-order80";
    }

    @Override
    public String paymentTimeout(Integer id) {
        return "服务调用失败，paymentTimeout 提示来自：cloud-consumer-feign-order80";
    }
}
