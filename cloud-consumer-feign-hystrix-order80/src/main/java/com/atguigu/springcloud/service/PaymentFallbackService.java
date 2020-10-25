package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author leowei
 * @date 2020/10/25  - 13:24
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    public String paymentInfo_OK(Integer id) {
        return "--PaymentFallbackService-- paymentInfo_OK ";
    }

    public String paymentInfo_TimeOut(Integer id) {
        return "--PaymentFallbackService-- paymentInfo_TimeOut ";
    }
}
