package com.xishanqu.redpacket.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author BaoNing 2019/7/8
 */
public class RedissonLockTest {

    public static final int fixNum = 5;

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(fixNum);
        RedissonClient redissonClient = Redisson.create();
        ExecutorService exec = Executors.newFixedThreadPool(fixNum);

        for (int i = 0; i < fixNum; i++) {
            exec.submit(new TestLock("client-" + i, redissonClient, latch));
        }

        exec.shutdown();
        latch.await();
        System.out.println("所有任务执行完毕");

    }

}
