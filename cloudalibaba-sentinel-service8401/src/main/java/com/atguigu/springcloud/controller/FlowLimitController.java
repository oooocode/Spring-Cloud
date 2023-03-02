package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: wth
 * @Create: 2023/2/28 - 15:28
 */
@RestController
@Slf4j
public class FlowLimitController {

    @Resource
    private OrderService orderService;

    @GetMapping("/testA")
    public String testA() {
        log.info("******testA");
        return "------testA" + orderService.myService();
    }

    @GetMapping("/testB")
    public String testB() {
        log.info("******testB");
        return "------testB" + orderService.myService();
    }

    @GetMapping("/testD")
    public String testD() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        int age = 1 / 0;
        log.info("******testD  测试 异常比例");
        return "------testB" + orderService.myService();
    }

    @GetMapping("/testE")
    public String testE() {
        int age = 1 / 0;
        log.info("******testE  测试 异常数");
        return "------testE" + orderService.myService();
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "------testHotKey";
    }
    public String deal_testHotKey(String p1, String p2, BlockException blockException) {
        return "------deal_testHotKey,o(╥﹏╥)o   ";

    }


}