package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: wth
 * @Create: 2023/2/19 - 9:56
 */
public interface PaymentService {

    /**
     * 创建支付
     * @param payment 支付实体
     * @return
     */
    long create(Payment payment);

    /**
     * 根据id查询支付
     * @param id
     * @return
     */
    Payment getPaymentById(@Param("id") Long id);
}
