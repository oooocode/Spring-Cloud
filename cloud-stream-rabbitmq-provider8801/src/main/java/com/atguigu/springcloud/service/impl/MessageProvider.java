package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author: wth
 * @Create: 2023/2/25 - 22:14
 */
@EnableBinding(Source.class) // 表示一个消息的发送管道的定义
@Slf4j
public class MessageProvider implements IMessageProvider {

    @Resource
    private MessageChannel output;    // 消息的发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("**********serial: " + serial);
        return null;
    }
}
