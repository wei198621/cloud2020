package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.OrderDao;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author leowei
 * @date 2020/11/10  - 22:57
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    // 創建訂單  扣減庫存  扣減賬戶   修改訂單狀態
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        //01 新建訂單
        System.out.println("-------->开始创建新订单");
        orderDao.create(order);

        //02 扣減庫存
        System.out.println("--------订单微服务开始调用库存,做扣减 count");
        storageService.decrease(order.getProductId(),order.getCount());
        System.out.println("-------订单微服务开始调用库存，做扣减 count  end");

        //03 扣減賬戶
        System.out.println("-------订单微服务开始调用账户，做扣减 money ");
        accountService.decrease(order.getUserId(),order.getMoney());
        System.out.println("-------订单微服务开始调用账户，做扣减 money  end");


        //04  修改訂單狀態 1-->0
        System.out.println("-------修改订单状态");
        orderDao.update(order.getUserId(),0);
        System.out.println("-------修改订单状态end");

        System.out.println("--------下订单结束了，哈哈哈哈");

    }
}



















