package com.xishanqu.redpacket.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author BaoNing 2019/7/9
 */
@ConfigurationProperties("kafka.topic")
@Data
public class KafkaTopicProperties {

    private String groupId;
    private String[] topicName;

}
