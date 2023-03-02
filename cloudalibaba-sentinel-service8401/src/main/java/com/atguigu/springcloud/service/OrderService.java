package com.atguigu.springcloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @Author: wth
 * @Create: 2023/2/28 - 23:24
 */
@Service
public class OrderService {

    @SentinelResource("myService")
    public String myService() {
        return "test service";
    }
}
