package com.liutao.controller;

import com.liutao.Service.UserService;
import com.liutao.entity.User;
import com.wordnik.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ribbon使用Hystrix测试Controller
 *
 * @author LIUTAO
 * @version 2017/5/24
 * @see
 * @since
 */
@RestController
@Api(value = "test ribbon hystrix")
@RequestMapping("/liutao/v1")
public class RibbonHystrixUserController {
    private Logger logger = Logger.getLogger(RibbonHystrixUserController.class);

    @Autowired
    UserService userService;


    /**
     * Ribbon调用方式使用Hystrix断路器
     * @param username
     * @return
     */
    @RequestMapping(value = "/hystrix/ribbon/userinfo/{username}",method = RequestMethod.GET)
    public User getUserInfoOfHystrixRibbon(@PathVariable("username") String username){
        logger.debug("enter getUserInfoOfHystrixRibbon");
        return userService.getUserInfo(username);
    }
}
