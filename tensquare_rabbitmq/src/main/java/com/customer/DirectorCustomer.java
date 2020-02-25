package com.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/24
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Component
//@RabbitListener(queues = "first1")
public class DirectorCustomer {

//    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("测试直接模式" + msg);
    }
}
