package com.xishanqu.redpacket.config;

import com.xishanqu.redpacket.bean.KafkaTopicProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author BaoNing 2019/7/9
 */
@Configuration
@EnableConfigurationProperties(KafkaTopicProperties.class)
public class KafkaTopicConfig {

    private final KafkaTopicProperties properties;

    public KafkaTopicConfig(KafkaTopicProperties properties) {
        this.properties = properties;
    }

    @Bean
    public String[] kafkaTopicName() {
        return properties.getTopicName();
    }

    @Bean
    public String topicGroupId() {
        return properties.getGroupId();
    }

}
