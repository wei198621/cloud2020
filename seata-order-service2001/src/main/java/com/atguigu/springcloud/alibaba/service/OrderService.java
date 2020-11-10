package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author leowei
 * @date 2020/11/10  - 22:51
 */

public interface OrderService {

    void create(Order order);
}
