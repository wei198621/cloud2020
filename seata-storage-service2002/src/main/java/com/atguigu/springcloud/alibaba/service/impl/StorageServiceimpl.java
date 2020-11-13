package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.StorageDao;
import com.atguigu.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author leowei
 * @date 2020/11/10  - 22:57
 */
@Service
@Slf4j
public class StorageServiceimpl implements StorageService  {

    @Resource
    private StorageDao storageDao;





    public void decrease(Long productId, Integer count) {
        System.out.println("库存扣减开始----");
        storageDao.decrease(productId,count);
        System.out.println("库存扣减结束----");
    }

}



















