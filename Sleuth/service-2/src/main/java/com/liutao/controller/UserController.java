package com.liutao.controller;

import brave.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liutao on 2015/8/27.
 */
@RestController
@RequestMapping("/liutao")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private Tracer tracer;

    @Autowired
    SpanCustomizer span;
    @GetMapping(value = "/userInfo")
    @ResponseBody
    public Map<String,Object> getUserInfo(@RequestParam("username") String username) {
        logger.debug("second provider");
        logger.info("username:"+username);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("userName",username);
        newTrace();
        try {
            showDemo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 演示Span的相关处理方法
     */
    public void showDemo() throws InterruptedException {

        //在trace中添加一个新的span
        ScopedSpan scopedSpan =  tracer.startScopedSpan("start new span");
        scopedSpan.tag("firstName","jock");
        Thread.sleep(200);
        scopedSpan.finish();

        //手动添加时间标签
        span.annotate("tx.started");

        //添加一个客户端span
        Span span = tracer.nextSpan().name("add a client span").kind(Span.Kind.CLIENT);
        span.tag("myrpc.version", "1.0.0");
        span.remoteServiceName("service simulation");
        span.remoteIpAndPort("172.2.4.1", 8008);
        span.start();
        span.finish();


    }


    /**
     * 使用brave创建新的span
     */
    public void newTrace(){
       new Thread(()->{
           Span span = tracer.newTrace().name("wahaha").start();
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           span.finish();
       }).start();
    }

}
