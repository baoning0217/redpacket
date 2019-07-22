package com.xishanqu.redpacket.service;

import com.xishanqu.redpacket.common.constant.RedisConstant;
import com.xishanqu.redpacket.pojo.RedPacket;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author BaoNing 2019/7/22
 */
@Service
@Slf4j
public class RedissonService {

    @Autowired
    private Redisson redisson;
    @Autowired
    private RedisTemplate redisTemplate;


    public RedPacket reduceRedPacket(Long redPacketId){
        //加锁的key
        String key = RedisConstant.Red_Packet + redPacketId + "";
        //获取锁
        RLock rLock = redisson.getLock(key);
        //设置60秒自动释放锁(默认是30秒自动过期)
        rLock.lock(60, TimeUnit.SECONDS);

        //业务逻辑
        RedPacket redPacket = (RedPacket)redisTemplate.opsForValue().get(key);
        if (redPacket.getStock() > 0){
            redPacket.setStock(redPacket.getStock() - 1);
        }
        redisTemplate.opsForValue().set(RedisConstant.Red_Packet + redPacket.getId() + "", redPacket);
        //解锁
        rLock.unlock();
        return redPacket;
    }


}
