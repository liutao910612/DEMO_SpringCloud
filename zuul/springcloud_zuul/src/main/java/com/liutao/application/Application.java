package com.liutao.application;

import com.liutao.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * 程序启动类
 *
 * @author LIUTAO
 * @version 2017/5/24
 * @see
 * @since
 */
@EnableZuulProxy
@SpringCloudApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 实例化过滤器
	 * @return
	 */
	@Bean
	public AccessFilter accessFilter(){
		return new AccessFilter();
	}

	/**
	 * 实例化error过滤器
	 * @return
	 */
	@Bean
	public ErrorFilter errorFilter(){
		return new ErrorFilter();
	}
}
