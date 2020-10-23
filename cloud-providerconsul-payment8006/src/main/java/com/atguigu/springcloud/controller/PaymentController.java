package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Author: tz_wl
 * Date: 2020/10/23 11:37
 * Content:
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Value("${server.port}")
    private String SERVER_PORT;

    @RequestMapping("/consul")
    public String paymentConsul(){
        return "com.sprringcloud with consul :"+ SERVER_PORT +"\t" + UUID.randomUUID().toString();
    }
}
