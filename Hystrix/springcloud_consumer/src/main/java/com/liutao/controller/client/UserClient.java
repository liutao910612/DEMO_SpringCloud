package com.liutao.controller.client;

import com.liutao.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Feign client
 * 注意Feign中自带断路器，从而我们可以直接使用
 *
 * @author LIUTAO
 * @version 2017/5/24
 * @see
 * @since
 */
@FeignClient(value = "liutao-provider" , fallback = UserClientHyStrix.class)
public interface UserClient {

    @RequestMapping(value = "/userInfo",method = RequestMethod.GET)
    @ResponseBody
    User getUserInfo(@RequestParam("username") String username);
}