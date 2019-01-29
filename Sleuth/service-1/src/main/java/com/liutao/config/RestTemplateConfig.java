package com.liutao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 配置RestTemplate
 *
 * @author LIUTAO
 * @version 2017/4/21
 * @see
 * @since
 */
@Configuration
public class RestTemplateConfig {

    @Value("${restTemplate.connectionRequestTimeout}")
    private int connectionRequestTimeout; //连接请求超时时间

    @Value("${restTemplate.connectionTimeout}")
    private int connectionTimeout;        //连接超时时间

    @Value("${restTemplate.readTimeout}")
    private int readTimeout;              //读取超时时间

    @Bean
    @LoadBalanced
    @Primary
    public RestTemplate customRestTemplate(){
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
        httpRequestFactory.setConnectTimeout(connectionTimeout);
        httpRequestFactory.setReadTimeout(readTimeout);
        return new RestTemplate(httpRequestFactory);
    }

}
