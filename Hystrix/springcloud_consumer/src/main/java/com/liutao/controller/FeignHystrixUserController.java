package com.liutao.controller;

import com.liutao.controller.client.UserClient;
import com.liutao.entity.User;
import com.liutao.exception.LinkException;
import com.wordnik.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Feign使用Hystrix测试Controller
 *
 * @author LIUTAO
 * @version 2017/5/24
 * @see
 * @since
 */
@RestController
@Api(value = "test feign hystrix")
@RequestMapping("/liutao/v1")
public class FeignHystrixUserController {
    private Logger logger = Logger.getLogger(FeignHystrixUserController.class);

    @Autowired
    private UserClient userClient;

    /**
     * 使用Feign
     * @param username
     * @return
     */
    @RequestMapping(value = "/userInfo/{username}",method = RequestMethod.GET)
    @ResponseBody
    public User getUserInfo(@PathVariable("username") String username){
        User user = userClient.getUserInfo(username);
        if(user!=null){
            logger.info("user.getAge():"+user.getAge());
        }
        return user;
    }
}
