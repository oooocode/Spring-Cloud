package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @Author: wth
 * @Create: 2023/2/19 - 11:17
 */
@RequestMapping("/consumer")
@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping("/payment/create")
    public CommonResult create(Payment payment) {
        if (payment == null) {
            return new CommonResult<>(401, "参数错误");
        }
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        if (id == null || id <= 0) {
            return new CommonResult<>(401, "参数错误");
        }
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/payment/createForEntity")
    public CommonResult create2(Payment payment) {
        if (payment == null) {
            return new CommonResult<>(401, "参数错误");
        }
        ResponseEntity<CommonResult> result = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if (!result.getStatusCode().is2xxSuccessful()) {
            return new CommonResult(444, "操作错误");
        }
        return result.getBody();
    }

    @GetMapping("/payment/getForEntity/{id}")
    public CommonResult getPaymentById2(@PathVariable("id") Long id) {
        if (id == null || id <= 0) {
            return new CommonResult<>(401, "参数错误");
        }
        // getForObject只返回json串，而getForEntity返回更多详细信息
        ResponseEntity<CommonResult> result = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (!result.getStatusCode().is2xxSuccessful()) {
            return new CommonResult<>(444, "操作失败");
        }
        return result.getBody();
    }

    @GetMapping("/payment/lb")
    public String testLb() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        String result = restTemplate.getForObject(uri + "/payment/lb", String.class);
        return result;
    }
}
