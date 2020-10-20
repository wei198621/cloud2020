package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leowei
 * @date 2020/10/20  - 23:30
 */

@Slf4j
@RequestMapping("/payment")
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String SERVER_PORT;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
       // log.info("***查询结果：" + payment);
        if(payment!=null){
            return new CommonResult(200,"查询数据成功，serverport:"+ SERVER_PORT,payment);
        }else{
            return new CommonResult(444,"没有对应记录",null);
        }
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if(result>0){
            return new CommonResult(200,""+SERVER_PORT,result);
        }else{
            return new CommonResult(444,"",null);
        }
    }





}
