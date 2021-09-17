package com.liang.iotmall.order;

import com.liang.iotmall.order.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class IotmallOrderApplicationTests {

    //创建队列，管理绑定关系
    @Autowired
    AmqpAdmin amqpAdmin;
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 如何创建Exchange、Queue、Bingding
     * param is null
     * @return: void
     */
    @Test
    void createExchange() {
        DirectExchange directExchange = new DirectExchange("directExchange",true,false);
        amqpAdmin.declareExchange(directExchange);
        log.info("Exchange创建成功");

    }

    @Test
    void createQueue() {
        Queue queue = new Queue("hello-queue",true, false,false);
        amqpAdmin.declareQueue(queue);
        log.info("队列创建成功");
    }

    @Test
    void createBind() {
        Binding binding = new Binding("hello-queue",Binding.DestinationType.QUEUE,"directExchange","hello.java",null) ;
        amqpAdmin.declareBinding(binding);
        log.info("绑定创建成功");
    }

    @Test
    void sendMessageTest() {
//        rabbitTemplate.convertAndSend("directExchange","hello.java","Hello,World!");
//        log.info("消息发送成功");
        //测试发送对象
        OrderEntity entity = new OrderEntity();
        entity.setBillContent("bgbgh");
        entity.setBillReceiverEmail("fdafadsf");
        rabbitTemplate.convertAndSend("directExchange","hello.java",entity);
        log.info("消息发送成功");
    }

}
