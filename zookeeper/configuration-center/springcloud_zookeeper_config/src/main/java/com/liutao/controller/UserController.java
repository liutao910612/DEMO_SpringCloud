package com.liutao.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liutao on 2015/8/27.
 */
@RefreshScope
@RestController
@Api(value = "test")
@RequestMapping("/liutao")
public class UserController {

    @Value("${username}")
    private String username;

    @Autowired
    private HttpServletRequest request;

    @GetMapping(value = "/property")
    @ResponseBody
    public Map<String,Object> getUserInfo() {
        System.out.println(request.getRemoteHost());
        System.out.println(request.getHeader("x-forwarded-for"));

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("username",username);
        return resultMap;
    }
}
