package com.liutao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 配置测试类
 *
 * @author: LIUTAO
 * @Date: Created in 2019/1/28  14:32
 * @Modified By:
 */

@RefreshScope
@Component
public class Config {

    @Value("${from}")
    private String from;

    public String getFrom() {
        return from;
    }

    public Config() {
        System.out.println("######################init config######################");
    }
}
