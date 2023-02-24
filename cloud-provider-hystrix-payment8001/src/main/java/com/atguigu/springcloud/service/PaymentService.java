package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wth
 * @Create: 2023/2/23 - 20:40
 */
@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id) {
        return "线程名:   " + Thread.currentThread().getName() + "id:" + id + " 成功";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    public String paymentInfo_timeout(Integer id) {
        int timeoutMill = 0;
//        int age = 10 / 0;
        try {
            TimeUnit.SECONDS.sleep(timeoutMill);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "线程号:   " + Thread.currentThread().getName() + "id:" + id + " 耗时:  ";
    }

    public String paymentInfo_timeoutHandler(Integer id) {
        return "线程号:   " + Thread.currentThread().getName() + "paymentInfo_timeoutHandler, 系统繁忙或异常 id:" + id + "o(╥﹏╥)o";
    }

    //=========服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), // 请求次数超过了峰值，熔断器将会从关闭
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),// 时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 错误率达到多少之后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }

}
