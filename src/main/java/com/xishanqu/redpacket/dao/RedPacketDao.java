package com.xishanqu.redpacket.dao;

import com.xishanqu.redpacket.pojo.RedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-07-11
 */
@Component
public class RedPacketDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加到Mongo缓存
     *
     * @param redPacket
     */
    public void saveRedPacket(RedPacket redPacket) {
        if (!ObjectUtils.isEmpty(redPacket)) {
            mongoTemplate.save(redPacket);
        }
    }


}
