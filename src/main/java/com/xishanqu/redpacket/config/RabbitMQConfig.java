package com.xishanqu.redpacket.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-07-13
 */
@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.topic.redpacket-topic}")
    public String rabbitmqTopicName;
    @Value("${rabbitmq.queue.redpacket-queue}")
    public String rabbitmqQueueName;
    @Value("${rabbitmq.routingKey.redpacket-routingKey}")
    public String rabbitmqRoutingKeyName;


    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(rabbitmqTopicName, true, false);
    }

    @Bean
    public Queue redPacket(){
        return new Queue(rabbitmqQueueName);
    }

    @Bean
    public Binding redPacketBinding(){
        return BindingBuilder.bind(redPacket()).to(topicExchange()).with(rabbitmqRoutingKeyName);
    }

}
