package com.liang.iotmall.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.liang.iotmall.order.entity.OrderEntity;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.common.utils.PageUtils;
import com.liang.common.utils.Query;

import com.liang.iotmall.order.dao.OrderItemDao;
import com.liang.iotmall.order.entity.OrderItemEntity;
import com.liang.iotmall.order.service.OrderItemService;


@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItemEntity> page = this.page(
                new Query<OrderItemEntity>().getPage(params),
                new QueryWrapper<OrderItemEntity>()
        );

        return new PageUtils(page);
    }

    @RabbitListener(queues = {"hello-queue"})
    public void recieveMessage(Message message, OrderEntity content){
        //消息头属性信息
        byte[] body = message.getBody();
        System.out.println("接收到的消息为"+message+"内容为"+content);
    }

}