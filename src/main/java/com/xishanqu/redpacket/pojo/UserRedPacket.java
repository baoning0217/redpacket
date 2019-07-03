package com.xishanqu.redpacket.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author BaoNing 2019/7/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRedPacket implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 红包编号
     */
    private Long redPacketId;

    /**
     * 抢红包用户编号
     */
    private Long userId;

    /**
     * 抢红包金额
     */
    private Double amount;

    /**
     * 抢红包时间
     */
    private Timestamp grabTime;

    /**
     * 备注
     */
    private String note;

}
