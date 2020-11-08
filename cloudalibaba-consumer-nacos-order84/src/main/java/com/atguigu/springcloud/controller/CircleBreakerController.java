package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author leowei
 * @date 2020/11/8  - 21:41
 */

@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVER_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;



    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value="fallback")  //没有配置
    //@SentinelResource(value="fallback",fallback = "handlerFallback")  //fallback 负责业务异常
    //@SentinelResource(value="fallback",blockHandler = "blockHandler")  //blockhandler 负责 sentinel 控制台违规
    @SentinelResource(value="fallback",fallback = "handlerFallback",blockHandler = "blockHandler"
    ,exceptionsToIgnore = {IllegalArgumentException.class})  //没有配置
    public CommonResult<Payment> fallback(@PathVariable Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVER_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if(id==4){
            throw new IllegalArgumentException("IllegalArgumentException ,非法参数异常....");
        }else if(result.data == null){
            throw new NullPointerException("NullPointerException ,该ID没有对应的记录，空指针异常");
        }
        return result;
    }


    //负责业务异常
    public CommonResult handlerFallback (@PathVariable Long id, Throwable e){
        Payment payment = new Payment(Integer.valueOf(id.toString()), "null");
        return new CommonResult(444,"兜底方法 handlerFallback,exception 内容是:  "+ e.getMessage(),payment);
    }
    //sentinel 控制台违规
    public CommonResult blockHandler (@PathVariable Long id, BlockException blockException){
        Payment payment = new Payment(Integer.valueOf(id.toString()), "null");
        return new CommonResult(445,"blockHandler-sentinel 限流，无此流水号： blockException 内容是:  "+ blockException.getMessage(),payment);
    }








}
