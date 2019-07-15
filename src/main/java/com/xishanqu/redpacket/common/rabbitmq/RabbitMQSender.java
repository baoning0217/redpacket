package com.xishanqu.redpacket.common.rabbitmq;

import com.xishanqu.redpacket.common.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-07-13
 */
@Component
@Slf4j
@RabbitListener(queues = "redpacket_queue_rabbitmq")
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;

    /**
     * RabbitTemplate
     * @param rabbitTemplate rabbitmq模版类
     */
    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送消息
     * @param messages
     */
    public void send(String messages){
        log.info("发送RabbitMQ消息队列>>>>>>>>>>>>>>>>>>>>message={}", messages);
        this.rabbitTemplate.convertAndSend(RabbitMqConstant.QUEUE, messages);
    }



}
