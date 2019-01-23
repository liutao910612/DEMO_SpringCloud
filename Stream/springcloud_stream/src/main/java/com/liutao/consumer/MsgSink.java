package com.liutao.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author: LIUTAO
 * @Date: Created in 2019/1/16  10:22
 * @Modified By:
 */
@EnableBinding(Sink.class)
public class MsgSink {

    private static Logger logger = LoggerFactory.getLogger(MsgSink.class);

    @StreamListener(Sink.INPUT)
    public void messageSink(Object payload){
        logger.debug("######################"+payload.toString());
    }
}
