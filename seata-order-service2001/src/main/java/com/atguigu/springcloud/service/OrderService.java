package com.atguigu.springcloud.service;

import com.atguigu.springcloud.domain.Order;

/**
 * @Author: wth
 * @Create: 2023/3/2 - 22:13
 */
public interface OrderService {

    /**
     * 创建订单
     * @param order
     */
    void create(Order order);
}
