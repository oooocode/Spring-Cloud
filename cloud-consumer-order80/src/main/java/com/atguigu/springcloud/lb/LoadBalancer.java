package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author: wth
 * @Create: 2023/2/21 - 15:09
 */
public interface LoadBalancer {

    /**
     * 获取服务实例中的一个
     * @param serviceInstances
     * @return
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
