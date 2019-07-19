package com.xishanqu.redpacket.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author BaoNing 2019/7/19
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.xishanqu.redpacket.mapper"})
public class MyBatisConfig {
}
