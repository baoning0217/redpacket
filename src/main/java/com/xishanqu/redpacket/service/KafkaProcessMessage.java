package com.xishanqu.redpacket.service;

import com.alibaba.fastjson.JSONObject;
import com.xishanqu.redpacket.pojo.RedPacket;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

/**
 * @Author BaoNing 2019/7/9
 */
@Service
@Slf4j
public class KafkaProcessMessage {

    /**
     * 消费消息
     * @param record
     */
    //@KafkaListener(topics = "${kafka.topic.redpacket-topic}", groupId = "${kafka.topic.group-id}")
    public void processMessage(ConsumerRecord<Integer, String> record) {
        log.info("kafka processMessage start");

        RedPacket redPacket = JSONObject.parseObject(record.value(), RedPacket.class);
        log.info("消费消息:>>>>>>>>>>>>>>redPacket={}", redPacket);

    }

}
