package com.liutao.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liutao on 2015/8/27.
 */
@RestController
@Api(value = "test")
@RequestMapping("/liutao")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/userInfo")
    @ResponseBody
    public Map<String,Object> getUserInfo(@RequestParam("username") String username) {
        logger.debug("second provider");
        logger.info("username:"+username);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("userName",username);
        return resultMap;
    }
}
