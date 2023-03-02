package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFallbackService;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author: wth
 * @Create: 2023/3/1 - 17:58
 */
@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private PaymentService paymentService;

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback") // 无配置
//    @SentinelResource(value = "fallback", fallback = "handleFallback") // 只配置fallback
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler") // 只配置fallback
    @SentinelResource(value = "fallback",
            fallback = "handleFallback",
            blockHandler = "blockHandler",
            exceptionsToIgnore = IllegalArgumentException.class) // 只配置fallback
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        }
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

    public CommonResult blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(445, "blockHandler-sentinel限流,无此流水: blockException  " + blockException.getMessage(), payment);
    }

    public CommonResult<Payment> handleFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(0L, null);
        return new CommonResult<>(404, "请求错误请稍后再试" + e.getMessage(), payment);
    }

    @GetMapping(value = "/consumer/openfeign/{id}")
    @SentinelResource(value = "openfeign",
            fallback = "paymentSQL1")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        if (id == 4) {
            throw new RuntimeException("没有该id");
        }
        return paymentService.paymentSQL(id);
    }

}

