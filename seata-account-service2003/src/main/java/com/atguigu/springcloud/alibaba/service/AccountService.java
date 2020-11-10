package com.atguigu.springcloud.alibaba.service;

import java.math.BigDecimal;

/**
 * @author leowei
 * @date 2020/11/11  - 1:20
 */
public interface AccountService {
    void decrease(Long userId, BigDecimal money);
}
