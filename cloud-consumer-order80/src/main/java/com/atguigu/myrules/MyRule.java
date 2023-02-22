package com.atguigu.myrules;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wth
 * @Create: 2023/2/21 - 11:42
 */
@Configuration
public class MyRule {

    @Bean
    public IRule getMyRule(){
        return new RandomRule();
    }
}
