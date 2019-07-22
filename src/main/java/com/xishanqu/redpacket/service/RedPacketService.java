package com.xishanqu.redpacket.service;

import com.alibaba.fastjson.JSON;
import com.xishanqu.redpacket.common.bean.MailInfo;
import com.xishanqu.redpacket.common.constant.RedisConstant;
import com.xishanqu.redpacket.common.kafka.KafkaService;
import com.xishanqu.redpacket.common.mail.MailService;
import com.xishanqu.redpacket.common.rabbitmq.RabbitMQSender;
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
    private RedPacketDao redPacketDao;
    @Autowired
    private RabbitMQSender rabbitMQSender;
    @Autowired
    private MailService mailService;
    @Autowired
    private RedisTemplate redisTemplate;



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
        //发送mail通知
        MailInfo mail = new MailInfo();
        log.info("发送邮件通知开始>>>>>>>>>>mail={}", mail);
        mailService.sendSimpleMail(mail.getFrom(), mail.getTo(), mail.getCc(), mail.getSubject(), mail.getContent());
        log.info("发送邮件通知完毕>>>>>>>>>>>>>>>mail={}", mail);

        return packet.getId().intValue();
    }

    /**
     * 获取红包信息
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public RedPacket getRedPacket(Long id){

        //从redis缓存里获取
        RedPacket redPacket_For_Redis = (RedPacket)redisTemplate.opsForValue().get(RedisConstant.Red_Packet + id + "");
        if (!ObjectUtils.isEmpty(redPacket_For_Redis)){
            return redPacket_For_Redis;
        }
        RedPacket redPacket = redPacketMapper.getRedPacket(id);
        if (!ObjectUtils.isEmpty(redPacket)){
            redisTemplate.opsForValue().set(RedisConstant.Red_Packet + redPacket.getId() + "", redPacket);
        }
        //添加到MongoDB缓存
//        if (!ObjectUtils.isEmpty(redPacket)) {
//            redPacketDao.saveRedPacket(redPacket);
//            log.info("saveRedPacket to MongoDB>>>>>>redPacket={}", redPacket);
//        }
        //发送rabbitmq消息
        log.info("发送RabbitMQ消息开始>>>>>>>>>>>start");
        rabbitMQSender.send(JSON.toJSONString(redPacket));
        log.info("发送RabbitMQ消息结束>>>>>>>>>>>end");
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
