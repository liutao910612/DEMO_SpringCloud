package com.liutao.controller.client;

import com.liutao.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "liutao-provider")
public interface UserClient {

    @RequestMapping(value = "/userInfo",method = RequestMethod.GET)
    @ResponseBody
    User getUserInfo(@RequestParam("username") String username);
}