package com.liutao.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;

/**
 * @author: LIUTAO
 * @Date: Created in 2019/1/24  14:16
 * @Modified By:
 */
@EnableBinding(Source.class)
public class GreetingSource {

    @Autowired
    private Source source;

    public void greet(String msg) {
        try {
            source.output().send(MessageBuilder.withPayload(msg).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
