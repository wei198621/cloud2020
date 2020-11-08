package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author leowei
 * @date 2020/11/8  - 23:10
 */

@Component
public class PaymentFallbackService implements PaymentService {
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<Payment>(444,"服务降级返回，PaymentFallbackService------",new Payment(Integer.valueOf(id.toString()),"error"));
    }
}
