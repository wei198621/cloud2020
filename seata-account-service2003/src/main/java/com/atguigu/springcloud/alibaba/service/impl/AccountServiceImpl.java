package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.AccountDao;
import com.atguigu.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author leowei
 * @date 2020/11/11  - 1:21
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    public void decrease(Long userId, BigDecimal money) {
        System.out.println("账户扣除余额开始---");
        //模擬超時
        /*try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //模擬報錯
        //int i=10/0;
        accountDao.decrease(userId, money);
        System.out.println("账户扣除余额结束---");
    }
}
