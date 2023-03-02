package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.mapper.OrderMapper;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Author: wth
 * @Create: 2023/3/2 - 22:13
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("******订单开始创建");
        orderMapper.create(order);

        log.info("------->order-service中扣减库存count开始");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("------->order-service中扣减库存count开始end");

        log.info("------->order-service中扣减账户余额money开始");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("------->order-service中扣减账户余额money开始end");

        log.info("*******修改订单状态开始");
        orderMapper.update(order.getUserId(),0);
        log.info("*******修改订单状态成功");

        log.info("******下订单结束，O(∩_∩)O哈哈~");
    }
}
