package com.atguigu.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Author: tz_wl
 * Date: 2020/10/21 14:04
 * Content:
 */
@Configuration
public class ApplicationContextConfig {

    //经过这不操作  spring 容器就会有   RestTemplate对象了
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
