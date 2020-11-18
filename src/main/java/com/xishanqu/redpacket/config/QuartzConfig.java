package com.xishanqu.redpacket.config;

import com.xishanqu.redpacket.common.job.MySecondJob;
import org.quartz.JobDataMap;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-07-14
 */
@Configuration
public class QuartzConfig {

    /**
     * 第一种形式
     *
     * @return
     */
    @Bean
    public MethodInvokingJobDetailFactoryBean jobDetail_One() {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("myFirstJob");
        bean.setTargetMethod("sayHello");
        return bean;
    }

    @Bean
    public SimpleTriggerFactoryBean simpleTrigger() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setJobDetail(jobDetail_One().getObject());
        //配置任务循环次数
        bean.setRepeatCount(3);
        //配置任务启动延时时间
        bean.setStartDelay(3000);
        //配置任务的时间间隔
        bean.setRepeatInterval(5000);
        return bean;
    }


    /**
     * 第二种形式
     *
     * @return
     */
    @Bean
    public JobDetailFactoryBean jobDetail_Two() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(MySecondJob.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name", "captain");
        bean.setJobDataMap(jobDataMap);
        bean.setDurability(false);
        return bean;
    }

//    @Bean
//    public CronTriggerFactoryBean cronTrigger(){
//        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
//        bean.setJobDetail(jobDetail_Two().getObject());
//        bean.setCronExpression("* * * * * ?");
////        //执行时间
////        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;
////        Date startDate = new Date(currentTime);
////        //设置定时器开始执行时间，启动后会一直执行
////        bean.setStartTime(startDate);
//        return bean;
//    }

    /**
     * 定时器构建
     *
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        SimpleTrigger simpleTrigger = simpleTrigger().getObject();
        //CronTrigger cronTrigger = cronTrigger().getObject();
        bean.setTriggers(simpleTrigger);
        return bean;
    }


}
