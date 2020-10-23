package com.atguigu.springcloud;

import com.atguigu.myruler.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Author: tz_wl
 * Date: 2020/10/21 13:55
 * Content:
 */

@SpringBootApplication
@EnableEurekaClient
//1.自定义访问规则  2.将访问规则注册到启动类上面  (此处是说，访问CLOUD-PAYMENT-SERVICE 的时候使用MySelf规则)
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
