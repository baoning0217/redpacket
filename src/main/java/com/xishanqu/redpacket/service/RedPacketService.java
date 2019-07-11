package com.xishanqu.redpacket.service;

import com.alibaba.fastjson.JSON;
import com.xishanqu.redpacket.dao.RedPacketDao;
import com.xishanqu.redpacket.mapper.RedPacketMapper;
import com.xishanqu.redpacket.pojo.RedPacket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * @Author BaoNing 2019/7/2
 */
@Service
@Slf4j
public class RedPacketService {

    @Autowired
    private RedPacketMapper redPacketMapper;
    @Autowired
    private KafkaService kafkaService;
    @Value("${kafka.topic.redpacket-topic}")
    private String topicName;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedPacketDao redPacketDao;


    /**
     * 创建插入红包
     * @param redPacket
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int addRedPacket(RedPacket redPacket){
        redPacketMapper.addRedPacket(redPacket);
        Long redPacketId = redPacket.getId();
        RedPacket packet = redPacketMapper.getRedPacket(redPacketId);
        //发送kafka消息
        kafkaService.sendMessage(topicName, JSON.toJSONString(packet));
        return packet.getId().intValue();
    }

    /**
     * 获取红包信息
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public RedPacket getRedPacket(Long id){
        RedPacket redPacket = redPacketMapper.getRedPacket(id);
        //添加到MongoDB缓存
        if (!ObjectUtils.isEmpty(redPacket)) {
            redPacketDao.saveRedPacket(redPacket);
            log.info("saveRedPacket to MongoDB>>>>>>redPacket={}", redPacket);
        }
        return redPacket;
    }

    /**
     * 扣减红包数
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int decreaseRedPacket(Long id){
        return redPacketMapper.decreaseRedPacket(id);
    }


}
