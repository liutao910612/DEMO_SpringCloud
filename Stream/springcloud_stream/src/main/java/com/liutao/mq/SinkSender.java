package com.liutao.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 定义消息通道
 *
 * @author: LIUTAO
 * @Date: Created in 2019/1/24  16:47
 * @Modified By:
 */
public interface SinkSender {

    String OUTPUT = "input";

    @Output(SinkSender.OUTPUT)
    MessageChannel output();

}