package com.liutao.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * 消费者实例
 *
 * @author: LIUTAO
 * @Date: Created in 2019/1/24  14:20
 * @Modified By:
 */
@EnableBinding(Sink.class)
public class LoggingSink {

    @StreamListener(Sink.INPUT)
    public void log(String message){
        System.out.println(message);
    }
}
