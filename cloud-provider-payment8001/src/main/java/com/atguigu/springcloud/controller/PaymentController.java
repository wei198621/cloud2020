package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.FormattedFloatingDecimal;

import java.util.List;
import java.util.concurrent.TimeUnit;

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


    //Eureka 服务发现（暴露） step 01
    @Autowired
    private EurekaDiscoveryClient discoveryClient;

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
      //  log.info("get/"+ id);
       // log.info("***查询结果：" + payment);
        if(payment!=null){
            return new CommonResult(200,"查询数据成功，serverport:"+ SERVER_PORT,payment);
        }else{
            return new CommonResult(444,"没有对应记录",null);
        }
    }

    //限制 只可以获取Payment 类型的 数据
    @GetMapping("/get2/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        // log.info("***查询结果：" + payment);
        if(payment!=null){
            return new CommonResult(200,"查询数据成功l9999，serverport:"+ SERVER_PORT,payment);
        }else{
            return new CommonResult(444,"没有对应记录55555",null);
        }
    }

    //直接通过postman 调用不需要用@RequestBody 作为前缀
    //此接口是直接调用的
    @PostMapping("/create4Direct")
    public CommonResult create4Direct(Payment payment){
     //   log.info("create4Direct/  id is "+ payment.getId() + " __ serial: "+ payment.getSerial());
        int result = paymentService.create(payment);
        if(result>0){
            return new CommonResult(200,"插入数据库成功，serverPort is : "+SERVER_PORT,result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }


    // 此接口服务提供接口8001  ,   供 80接口调用，80接口过来后 需要使用  @RequestBody

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        //log.info("create/  id is "+ payment.getId() + " __ serial: "+ payment.getSerial());
        int result = paymentService.create(payment);
        if(result>0){
            return new CommonResult(200,"插入数据库成功，serverPort is : "+SERVER_PORT,result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    //Eureka 服务发现（暴露） step 02
    @GetMapping("/discovery")
    public void discovery(){
        List<String> services = discoveryClient.getServices();
        for(String element : services){
           // log.info("element:\t"+ element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
           // log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+ instance.getPort()+"\t"+instance.getUri());
        }
    }



    @GetMapping(value = "/lb")
    public String getPaymentLB(){
        return SERVER_PORT;
    }



    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            // 暂停3秒钟
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return SERVER_PORT;
    }

    @GetMapping("/zipkin")
    public String paymentZipkin() {
        return "hellp zipkin";
    }

}
