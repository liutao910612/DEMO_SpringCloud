package com.liutao.controller.client;

import com.liutao.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign client 熔断器类
 *
 * @author LIUTAO
 * @version 2017/5/24
 * @see
 * @since
 */
@Component
public class UserClientHyStrix implements UserClient {
    @Override
    public User getUserInfo(@RequestParam("username") String username) {
        return new User();
    }
}
