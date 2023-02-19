package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: wth
 * @Create: 2023/2/19 - 9:58
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/create")
    public CommonResult<Long> create(@RequestBody Payment payment){
        if (payment == null){
            return new CommonResult<>(401, "请求参数为空哈哈");
        }
        long paymentId = paymentService.create(payment);
        log.info("插入结果为:" + payment);
        if (paymentId <= 0) {
            return new CommonResult<>(404, "添加失败", null);
        }
        return new CommonResult<>(200, "添加成功", paymentId);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        if (id == null || id <= 0){
            return new CommonResult<>(401, "请求参数错误");
        }
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果为: " + payment);
        if (payment == null) {
            return new CommonResult<>(404, "查询无结果", null);
        }
        return new CommonResult<>(200, "查询成功", payment);
    }
}
