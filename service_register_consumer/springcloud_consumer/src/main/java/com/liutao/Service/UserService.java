package com.liutao.Service;

import com.liutao.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 用户服务层
 *
 * @author LIUTAO
 * @version 2017/4/18
 * @see
 * @since
 */
@Component
public class UserService {
    Logger logger = Logger.getLogger(UserService.class);
    @Autowired
    RestTemplate restTemplate;

    public User getUserInfo(String username){
        logger.info("enter getUserInfo of hystrix");
        User user = restTemplate.getForEntity("http://provider/liutao/userInfo?username="+username, User.class).getBody();

        if(user!=null){
            logger.info("user.getAge():"+user.getAge());
        }
        return user;
    }
}
