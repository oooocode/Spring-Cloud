package com.atguigu.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.rmi.MarshalException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: wth
 * @Create: 2023/2/21 - 19:25
 */
@Component
@Slf4j
public class MyLb implements LoadBalancer {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        // 第几次访问
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        log.info("********第" + next + "次访问");
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        // 访问次数和总服务实体取余，得到轮训的服务
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }

}
