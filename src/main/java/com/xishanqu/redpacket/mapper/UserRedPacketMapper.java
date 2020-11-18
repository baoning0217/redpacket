package com.xishanqu.redpacket.mapper;

import com.xishanqu.redpacket.pojo.UserRedPacket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRedPacketMapper {

    /**
     * 插入抢红包信息
     *
     * @param userRedPacket
     * @return
     */
    int grabRedPacket(UserRedPacket userRedPacket);


}