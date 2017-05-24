package com.liutao.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 程序启动类
 *
 * @author LIUTAO
 * @version 2017/5/23
 * @see
 * @since
 */
@EnableFeignClients(basePackages={"com.liutao.controller.client"})
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={"com.liutao"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
