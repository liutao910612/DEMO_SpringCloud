package com.liutao.controller;

import com.liutao.producer.GreetingSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者实例
 *
 * @author: LIUTAO
 * @Date: Created in 2019/1/24  16:47
 * @Modified By:
 */
@RestController
public class ShopService {

    @Autowired
    private GreetingSource greetingSource;

    @GetMapping("/msg")
    public String sendShopMessage(@RequestParam("content") String content) {
        greetingSource.greet(content);
        return "发送成功";
    }
}