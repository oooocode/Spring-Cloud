package com.atguigu.springcloud.mapper;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: wth
 * @Create: 2023/2/19 - 9:25
 */
@Mapper
public interface PaymentMapper {

    /**
     * 创建支付
     * @param payment 订单支付
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
