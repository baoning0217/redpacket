package com.xishanqu.redpacket.service;

import com.xishanqu.redpacket.mapper.RedPacketMapper;
import com.xishanqu.redpacket.mapper.UserRedPacketMapper;
import com.xishanqu.redpacket.pojo.RedPacket;
import com.xishanqu.redpacket.pojo.UserRedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author BaoNing 2019/7/2
 */
@Service
public class UserRedPacketService {

    @Autowired
    private UserRedPacketMapper userRedPacketMapper;
    @Autowired
    private RedPacketMapper redPacketMapper;

    private static final int FAILED = 0;

    /**
     * 插入抢红包信息
     * @param redPacketId
     * @param userId
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int grabRedPacket(Long redPacketId, Long userId){
        //记录开始时间
        long start = System.currentTimeMillis();
        //无限循环，等待成功或者时间满100毫秒退出
        while (true){
            //获取循环当前时间
            long end = System.currentTimeMillis();
            //当前时间已经超过100毫秒，返回失败
            if ( (end - start) > 100){
                return FAILED;
            }
            //获取红包信息
            RedPacket redPacket = redPacketMapper.getRedPacket(redPacketId);

            //当前红包库存大于0
            if (redPacket.getStock() > 0){
                //乐观锁
                //再次传入线程保存的version旧值给SQL判断，是否有其他线程修改过数据
                int update = redPacketMapper.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());

                if (update == 0){
                    continue;
                }
                //生成抢红包信息
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(redPacket.getUnitAmount());
                userRedPacket.setNote("抢红包" + redPacketId);
                //插入抢红包信息
                int result = userRedPacketMapper.grabRedPacket(userRedPacket);
                return result;
            }else {
                //没有库存，则马上返回
                return FAILED;
            }
        }
    }


}
