package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.myhandler.CustomerBlockHandler;
import jdk.nashorn.internal.ir.Block;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

/**
 * @author leowei
 * @date 2020/11/8  - 12:11
 *
 * 流量控制 controller
 *
 */

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value ="byResource",blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200,"按資源名稱限流測試OK",new Payment(2020,"serial001"));
    }

    public CommonResult handleException(BlockException exception) {
        return new CommonResult(44444,exception.getClass().getCanonicalName()+ "\t 服務不可用");
    }



    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value ="byResource")
    public CommonResult byUrl() {
        return new CommonResult(200,"按url限流測試OK",new Payment(2020,"serial001"));
    }



    @GetMapping("/rateLimit/byCustomerBlockHandler")
    @SentinelResource(value ="byCustomerBlockHandler",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException")
    public CommonResult byCustomerBlockHandler() {
        return new CommonResult(200,"按byCustomerBlockHandler ",new Payment(2020,"serial001"));
    }




}
