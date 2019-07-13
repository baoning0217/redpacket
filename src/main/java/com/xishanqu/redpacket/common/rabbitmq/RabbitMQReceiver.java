package com.xishanqu.redpacket.common.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.xishanqu.redpacket.dao.RedPacketDao;
import com.xishanqu.redpacket.pojo.RedPacket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Function: RabbitMQ消费消息
 * @Author: BaoNing
 * @Date: 2019-07-13
 */
@Component
@Slf4j
@RabbitListener(queues = "prod_redpacket_queue_rabbitmq")
public class RabbitMQReceiver {
    @Autowired
    private RedPacketDao redPacketDao;

    @RabbitHandler
    public void processMessages(String message){
        log.info("processMessages>>>>>>>>>>>>message={}", message);
        RedPacket redPacket = JSON.parseObject(message, RedPacket.class);
        redPacketDao.saveRedPacket(redPacket);
        log.info("RabbitMQ消费消息,保存RedPacket>>>>>>>>>>redPacket={}", redPacket);
    }


}
