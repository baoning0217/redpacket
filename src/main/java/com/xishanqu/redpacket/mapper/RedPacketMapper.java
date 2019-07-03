package com.xishanqu.redpacket.mapper;


import com.xishanqu.redpacket.pojo.RedPacket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RedPacketMapper {

    /**
     * 获取红包信息
     * @param id
     * @return
     */
    RedPacket getRedPacket(Long id);

    /**
     * 扣减红包数
     * @param id
     * @return
     */
    int decreaseRedPacket(Long id);

    /**
     * 创建红包
     * @param redPacket
     * @return
     */
    int insertRedPacket(RedPacket redPacket);


    /**
     * 悲观锁
     * @param id
     * @return
     */
    RedPacket getRedPacketForUpdate(Long id);


    /**
     * 乐观锁
     * @param id
     * @param version
     * @return
     */
    int decreaseRedPacketForVersion(Long id, Integer version);


    int updateByPrimaryKey(RedPacket redPacket);

}