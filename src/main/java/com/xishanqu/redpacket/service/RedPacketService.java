package com.xishanqu.redpacket.service;

import com.xishanqu.redpacket.mapper.RedPacketMapper;
import com.xishanqu.redpacket.pojo.RedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author BaoNing 2019/7/2
 */
@Service
public class RedPacketService {

    @Autowired
    private RedPacketMapper redPacketMapper;

    /**
     * 创建插入红包
     * @param redPacket
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertRedPacket(RedPacket redPacket){
        return redPacketMapper.insertRedPacket(redPacket);
    }

    /**
     * 获取红包信息
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public RedPacket getRedPacket(Long id){
        return redPacketMapper.getRedPacket(id);
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
