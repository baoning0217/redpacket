package com.xishanqu.redpacket.service;

import com.xishanqu.redpacket.mapper.UserRedPacketMapper;
import com.xishanqu.redpacket.pojo.UserRedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author BaoNing 2019/7/3
 */
@Service
public class RedisRedPacketService {

    private static final String REDIS_PREFIX = "red_packet_list_";

    /**
     * 每次取出1000条，避免一次取出太多消耗太多内存
     */
    private static final int TIME_SIZE = 1000;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserRedPacketMapper userRedPacketMapper;

    /**
     * 开启新线程运行
     */
    @Async
    public void saveUserRedPackByRedis(Long redPacketId, Double unitAmount) {
        System.err.println("开始保存数据");
        Long start = System.currentTimeMillis();
        //获取列表操作对象
        BoundListOperations ops = redisTemplate.boundListOps(REDIS_PREFIX + redPacketId);
        Long size = ops.size();

        Long times = size % TIME_SIZE == 0 ? size / TIME_SIZE : size / TIME_SIZE + 1;

        int count = 0;
        List<UserRedPacket> userRedPacketList = new ArrayList<UserRedPacket>(TIME_SIZE);

        for (int i = 0; i < times; i++) {
            // 获取至多TIME_SIZE个抢红包信息
            List userIdList = null;
            if (i == 0) {
                userIdList = ops.range(i * TIME_SIZE, (i + 1) * TIME_SIZE);
            } else {
                userIdList = ops.range(i * TIME_SIZE + 1, (i + 1) * TIME_SIZE);
            }
            userRedPacketList.clear();
            // 保存红包信息
            for (int j = 0; j < userIdList.size(); j++) {
                String args = userIdList.get(j).toString();
                String[] arr = args.split("-");
                String userIdStr = arr[0];
                String timeStr = arr[1];
                Long userId = Long.parseLong(userIdStr);
                Long time = Long.parseLong(timeStr);
                // 生成抢红包信息
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(unitAmount);
                userRedPacket.setGrabTime(new Timestamp(time));
                userRedPacket.setNote("抢红包 " + redPacketId);
                userRedPacketList.add(userRedPacket);
            }
            // 插入抢红包信息
            count = executeBatch(userRedPacketList);
        }
        // 删除Redis列表
        redisTemplate.delete(REDIS_PREFIX + redPacketId);
        Long end = System.currentTimeMillis();
        System.err.println("保存数据结束，耗时" + (end - start) + "毫秒，共" + count + "条记录被保存。");
    }


    /**
     * 使用批量处理Redis缓存数据.
     *
     * @param userRedPacketList
     * @return 抢红包插入数量.
     */
    private int executeBatch(List<UserRedPacket> userRedPacketList) {
        int count = 0;
        for (UserRedPacket userRedPacket : userRedPacketList) {
            int i = userRedPacketMapper.grabRedPacket(userRedPacket);
            count = count + i;
        }
        // 返回插入抢红包数据记录
        return count;
    }


}
