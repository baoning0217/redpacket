package com.xishanqu.redpacket.common.job;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-07-14
 */
@Component
public class MyFirstJob {

    public void sayHello() {
        System.out.println("MyFirstJob:sayHello:" + new Date());
    }

}
