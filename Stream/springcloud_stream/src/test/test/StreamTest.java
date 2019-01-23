package test;

import com.liutao.mq.SinkSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: LIUTAO
 * @Date: Created in 2019/1/16  11:39
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@EnableBinding(value = {SinkSender.class})
public class StreamTest {

    @Autowired
    private SinkSender sinkSender;

    @Test
    public void sinkSenderTester() {
        sinkSender.output().send(MessageBuilder.withPayload("produce a message ：http://blog.didispace.com").build());
    }

}