package com.atguigu.springcloud.controller;

import cn.hutool.Hutool;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * Author: tz_wl
 * Date: 2020/10/21 13:58
 * Content:
 */

@RestController
@Slf4j
public class OrderController {

    //private final static String PAYMENT_URL="http://localhost:8001";  //非集群方式
    //集群  将ip:port 改为服务名 ,服务名可以是第一个对多个 ip:port
    private final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    //注入并实例化   Autowired 也可以
    @Resource
    private RestTemplate restTemplate;


    @Autowired
    private LoadBalancer loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;



    //getForObject 返回的直接是json字符串
    //getForEntity 放回的数据 ResponseEntity 封装了json字符串  带有Status等数据 ，更加细粒度

    //***************************   getForObject写法  *****************************************

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
       // log.info("/consumer/payment/get/"+ id);
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class,id);
    }

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        //log.info("/consumer/payment/create  serial  is "+ payment.getSerial()+ " __ id is "+ payment.getId());
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }


    //***************************  getForEntity写法   *****************************************

    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> result = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class, id);
        if(result.getStatusCode().is2xxSuccessful()){
            return result.getBody();
        }else{
            return new CommonResult<Payment>(444,"查询操作失败");
        }
    }

    @PostMapping("/consumer/payment/createEntity")
    public CommonResult<Payment> create2(Payment payment){
        ResponseEntity<CommonResult> result = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if(result.getStatusCode().is2xxSuccessful()){
            return result.getBody();
        }else{
            return  new CommonResult<Payment>(444,"保存操作失败");
        }
    }


    //*********************

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }
}
