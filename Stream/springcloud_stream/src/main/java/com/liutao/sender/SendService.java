package com.liutao.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;

/**
 * @author: LIUTAO
 * @Date: Created in 2019/1/16  10:18
 * @Modified By:
 */
@EnableBinding(Source.class)
public class SendService {

    @Autowired
    private Source source;

    public void sendMessage(String message){
        source.output().send(MessageBuilder.withPayload(message).build());
    }
}
