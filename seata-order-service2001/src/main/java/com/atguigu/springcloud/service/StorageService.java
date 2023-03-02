package com.atguigu.springcloud.service;

import com.atguigu.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: wth
 * @Create: 2023/3/2 - 22:28
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {


    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam(value = "productId") long productId,
                          @RequestParam(value = "count") int count);
}
