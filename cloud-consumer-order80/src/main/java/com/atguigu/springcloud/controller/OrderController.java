package com.atguigu.springcloud.controller;

import cn.hutool.Hutool;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Author: tz_wl
 * Date: 2020/10/21 13:58
 * Content:
 */

@RestController
@Slf4j
public class OrderController {


    private final static String PAYMENT_URL="http://localhost:8001";  //非集群方式

    //注入并实例化   Autowired 也可以
    @Resource
    private RestTemplate restTemplate;


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("/consumer/payment/get/"+ id);
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class,id);
    }

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){

        log.info("/consumer/payment/create  serial  is "+ payment.getSerial()+ " __ id is "+ payment.getId());
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }





}
