package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.mapper.PaymentMapper;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: wth
 * @Create: 2023/2/19 - 9:56
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public long create(Payment payment) {
        return paymentMapper.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
