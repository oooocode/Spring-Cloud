package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

/**
 * @Author: wth
 * @Create: 2023/3/2 - 22:28
 */
@Service
public interface StorageService {

    void decrease(Long productId, Integer count);

}
