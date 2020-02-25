package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/24
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class MqProduct {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直接模式通过exchange 到 queues 是一种特殊情况 exchange 为空“”
     */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("first","明天挺好的");
    }

    /**
     * 分裂模式 通过exchange 绑定到队列 可以 一对多
     *  one 为exchange 已经绑定了first second third
     */
    @Test
    public void fanout(){
        rabbitTemplate.convertAndSend("one",null,"分裂模式");
    }
    /**
     * 主题模式 通过exchange 绑定到队列 可以 一对多
     *  one 为exchange 已经绑定了first second third
     *  比分裂模式 routingkey 匹配的规则
     */
    @Test
    public void topic(){
        rabbitTemplate.convertAndSend("one","good.#","主题模式");
    }

}
