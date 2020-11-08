package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * @author leowei
 * @date 2020/11/8  - 12:11
 *
 * 流量控制 controller
 *
 */

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "-----testA"+ " --- "+ LocalTime.now().toString();
    }


    @GetMapping("/testB")
    public String testB() {
        System.out.println(Thread.currentThread().getName()+" testB   "+ LocalTime.now().toString());
        return "-----testB"+ " --- "+ LocalTime.now().toString();
    }

    @GetMapping("/testD")
    public String testD() {
        //每次休息1秒種
        /*try {            TimeUnit.SECONDS.sleep(1);        } catch (InterruptedException e) {            e.printStackTrace();        }
        System.out.println(Thread.currentThread().getName()+" testD 測試RT  "+ LocalTime.now().toString());*/

        System.out.println(Thread.currentThread().getName()+" testD 測試異常比例 "+ LocalTime.now().toString());
        int age= 10/0;
        return "-----testD"+ " --- "+ LocalTime.now().toString();
    }



    @GetMapping("/testE")
    public String testE() {
        //每次休息1秒種
        /*try {            TimeUnit.SECONDS.sleep(1);        } catch (InterruptedException e) {            e.printStackTrace();        }
        System.out.println(Thread.currentThread().getName()+" testD 測試RT  "+ LocalTime.now().toString());*/

        System.out.println(Thread.currentThread().getName()+" testE 測試異常數 "+ LocalTime.now().toString());
        int age= 10/0;
        return "-----testE"+ " --- "+ LocalTime.now().toString();
    }


    //兜底方法  deal_testHotKey
    @GetMapping("/testHotKey")
   // @SentinelResource(value="testHostKey")  // 不使用 blockHandler 跳轉的頁面是默認的錯誤頁面，不友好,不推薦使用
    @SentinelResource(value="testHostKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value="p1",required = false) String p1,
                             @RequestParam(value="p2",required = false) String p2){
        return "---------testHotKey";
    }

    //兜底方法  deal_testHotKey
    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "-------deal_testHotKey";
    }



}
