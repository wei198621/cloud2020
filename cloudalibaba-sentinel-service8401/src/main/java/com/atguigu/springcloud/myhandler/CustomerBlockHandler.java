package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

/**
 * @author leowei
 * @date 2020/11/8  - 18:07
 */
public class CustomerBlockHandler {


    // 注意此處要 static   555
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(444,"按客戶自定義  global  handlerException   -------1 ");
    }

    // 注意此處要 static
    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444,"按客戶自定義  global  handlerException  -------2 ");
    }
}
