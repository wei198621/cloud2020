package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
    //@LoadBalanced    //之前是官方的负载均衡，现在需要人工手写 将其注释掉
    //负载均衡 配置了这个 80 访问 paymentService 就会有个(默认)轮询机制，循环选择8001  8002 ，也可以在主函数中配置
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
