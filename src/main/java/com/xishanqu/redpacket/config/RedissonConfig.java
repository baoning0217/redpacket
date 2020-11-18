package com.xishanqu.redpacket.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author BaoNing 2019/7/22
 */
@Configuration
public class RedissonConfig {

    @Value(value = "${spring.redis.host}")
    private String host;
    @Value(value = "${spring.redis.port}")
    private String port;
    @Value(value = "${spring.redis.password}")
    private String password;
    @Value(value = "${spring.redis.database}")
    private Integer database;

    @Bean
    public Redisson redissonSentinel() {
        Config config = new Config();

//        config.useClusterServers()
//                //设置状态扫描时间间隔，单位毫秒
//                .setScanInterval(2000)
//                .addNodeAddress("redis://192.168.207.40:6379", "redis://192.168.207.40:6380");

        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("http://" + host + ":" + port);
        singleServerConfig.setPassword(password);
        singleServerConfig.setDatabase(database);
        RedissonClient redissonClient = Redisson.create(config);
        return (Redisson) redissonClient;
    }

}
