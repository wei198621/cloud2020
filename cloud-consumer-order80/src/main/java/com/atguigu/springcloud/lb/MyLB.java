package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author leowei
 * @date 2020/10/24  - 0:43
 */
@Component
public class MyLB implements LoadBalancer {

private AtomicInteger atomicInteger=new AtomicInteger(0);

private final int getIndIncrement(){
    int current;
    int next;

    do {
        current = this.atomicInteger.get();
        next = current >= Integer.MAX_VALUE ? 0 : current + 1;
    } while (!atomicInteger.compareAndSet(current, next));
    System.out.println("第几次访问,次数next:" + next);
    return next;

}



    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = this.getIndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
