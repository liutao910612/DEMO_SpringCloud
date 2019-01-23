package com.liutao.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 程序启动类
 *
 * @author LIUTAO
 * @version 2017/5/23
 * @see
 * @since
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={"com.liutao"})
public class Application {

	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	public void login(){
		Map<String,String> m = new HashMap<>();
		m.put("vid","1812311643jmhcog");
		m.put("oid","234221");

		String url = "";

	}

}
