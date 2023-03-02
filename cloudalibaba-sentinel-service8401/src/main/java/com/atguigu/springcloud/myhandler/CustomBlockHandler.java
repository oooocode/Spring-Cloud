package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

/**
 * @Author: wth
 * @Create: 2023/3/1 - 17:09
 */
public class CustomBlockHandler {

    public static CommonResult<Payment> handleException(BlockException exception) {
        return new CommonResult<>(200, "自定义的限流处理信息--------global1");
    }

    public static CommonResult<Payment>  handleException2(BlockException exception) {
        return new CommonResult<>(200, "自定义的限流处理信息--------global2");
    }
}
