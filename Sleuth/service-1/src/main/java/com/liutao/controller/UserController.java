package com.liutao.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 用户Controller
 *
 * @author LIUTAO
 * @version 2017/4/18
 * @see
 * @since
 */
@RestController
@Api(value = "test")
@RequestMapping("/liutao/v1")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用Ribbon实现客户端负载均衡的消费者
     * @param username
     * @return
     */
    @RequestMapping(value = "/ribbon/userInfo/{username}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUserInfoOfRibbon(@PathVariable("username") String username) {
        Map<String,Object> user = restTemplate.getForEntity("http://service-2/liutao/userInfo?username="+username, Map.class).getBody();
        return user;
    }

}
