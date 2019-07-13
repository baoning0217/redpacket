package com.xishanqu.redpacket.common.kafka;

import com.alibaba.fastjson.JSONObject;
import com.xishanqu.redpacket.common.constant.RedisConstant;
import com.xishanqu.redpacket.pojo.RedPacket;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @Author BaoNing 2019/7/9
 */
@Service
@Slf4j
public class KafkaProcessMessage {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 消费消息
     * @param record
     */
    @KafkaListener(topics = "${kafka.topic.redpacket-topic}", groupId = "${kafka.topic.group-id}")
    public void processMessage(ConsumerRecord<Integer, String> record) {
        log.info("kafka processMessage start");

        RedPacket redPacket = JSONObject.parseObject(record.value(), RedPacket.class);
        log.info("消费消息:>>>>>>>>>>>>>>redPacket={}", redPacket);
        //添加缓存
        redisTemplate.opsForValue().set(RedisConstant.Red_Packet + redPacket.getId() + "", redPacket);
        log.info("添加缓存:>>>>>>>>>>>>>>redPacket={}", redPacket);
        log.info("kafka processMessage end");

    }

}
