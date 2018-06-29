package com.liutao.controller.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "liutao-provider")
@RequestMapping("/liutao")
public interface UserClient {

    @GetMapping(value = "/userInfo")
    @ResponseBody
    Map<String,Object> getUserInfo(@RequestParam("username") String username);
}