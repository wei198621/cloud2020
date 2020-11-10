package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author leowei
 * @date 2020/11/10  - 22:31
 */
@Mapper
public interface OrderDao {
    //创建订单
    void create(Order order);

    //修改订单状态
    void update(@Param("userId") Long userId, @Param("status") Integer status);


}
