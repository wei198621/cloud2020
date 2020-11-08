package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author leowei
 * @date 2020/11/7  - 12:27
 */

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        return "nacos register, serverport=" + serverPort + "\t id:" + id;
    }


    public static HashMap<Long,Payment> hashMap=new HashMap<Long, Payment>();
    static{
        hashMap.put(1L,new Payment(1,"1111111111"));
        hashMap.put(2L,new Payment(2,"2222222222"));
        hashMap.put(3L,new Payment(3,"333333333333"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult result = new CommonResult(200, "from mysql,serverPort: " + serverPort, payment);
        return result;
    }



}
