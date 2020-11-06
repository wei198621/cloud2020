package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author leowei
 * @date 2020/11/5  - 23:07
 * 作为消息生产者
 * 将消息放到MessageChannel 里面 send 出去
 * Binding(Source)   开启输出源通道    -- 要对应 springcloud stream 流程图看
 * 所以整个过程是 开启输出源通道  通过 MessageChannel 将 消息绑定器中的消息 发送出去
 */

@EnableBinding(Source.class)         //开启输出源通道
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;    //消息发送通道  (注意此处的变量名称必须是output ,不然报错，原因待查，应该是约定大于配置)

    public String send() {
        String serial = UUID.randomUUID().toString();
        Message<String> message = MessageBuilder.withPayload(serial).build();   //消息绑定器，将消息绑定起来
        output.send(message);
        System.out.println("serial = " + serial);
        return serial;
    }
}
