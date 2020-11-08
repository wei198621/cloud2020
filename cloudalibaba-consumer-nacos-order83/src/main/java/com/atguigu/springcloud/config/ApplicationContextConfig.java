package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author leowei
 * @date 2020/11/7  - 13:21
 *
 * nacos 集成了 ribbon
 * ribbon 可以使用 RestTemplate   及其  负载均衡 ，需要在 与 controller 不同的 单独的命名空间下 增加configuration ,注册次bean
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced        //  restTemplate 结合ribbon 做负载均衡的时候 要加次注解
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
