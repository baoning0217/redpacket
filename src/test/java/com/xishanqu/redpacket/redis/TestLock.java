package com.xishanqu.redpacket.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author BaoNing 2019/7/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestLock implements Runnable {

    private String name;
    private RedissonClient redissonClient;
    private CountDownLatch latch;

    public void run() {
        //定义锁
        RLock lock = redissonClient.getLock("TestLock");
        //Redisson的分布式可重入锁RLock
        try {
            System.out.println("----------" + this.name + "------------等待获取锁-------------");
            //获取锁
            if (lock.tryLock(300, 30, TimeUnit.MILLISECONDS)) {
                //尝试加锁，最多等待300毫秒，上锁后30毫秒自动解锁
                try {
                    System.out.println("-----------" + this.name + "-----获得锁，开始处理---------");
                    //模拟处理业务逻辑所要时间
                    Thread.sleep(5 * 1000);
                    System.out.println("------------" + this.name + "-----------锁使用完毕------------");
                    latch.countDown();
                } finally {
                    //释放锁
                    lock.unlock();
                    System.out.println("------------" + this.name + "-----释放锁------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
