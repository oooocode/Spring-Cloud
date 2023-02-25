package com.atguigu.springcloud.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


/**
 * @Author: wth
 * @Create: 2023/2/25 - 22:46
 */
@Component
@EnableBinding(Sink.class) // 表示一个消息的接收者(消费者)
public class ReceiveMessgaeListener {

    @Value("${server.port}")
    private String serverPort;


    @StreamListener(Sink.INPUT)
    public void input(Message<String>message) {
        System.out.println("消费者2号：------》接收到的信息 " + message.getPayload() + "\tport: " + serverPort);
    }

}
