package com.xishanqu.redpacket.common.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @Author BaoNing 2019/7/9
 */
@Service
@Slf4j
public class KafkaService {

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    /**
     * 注入KafkaTemplate
     * @param kafkaTemplate kafka模版类
     */
    @Autowired
    public KafkaService(KafkaTemplate<Integer, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String data) {
        log.info("kafka sendMessage start");
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("kafka sendMessage error, ex = {}, topic = {}, data = {}>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", ex, topic, data);
            }
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                log.info("kafka sendMessage success topic = {}, data = {}>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",topic, data);
            }
        });
        log.info("kafka sendMessage end");
    }

}
