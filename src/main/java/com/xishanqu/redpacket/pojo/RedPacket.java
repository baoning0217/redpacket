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
public class RedPacket implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 红包编号
     */
    private Long id;

    /**
     * 发红包用户
     */
    private Long userId;

    /**
     * 红包金额
     */
    private Double amount;

    /**
     * 发红包时间
     */
    private Timestamp sendDate;

    /**
     * 小红包总数
     */
    private Integer total;

    /**
     * 单个小红包金额
     */
    private Double unitAmount;

    /**
     * 剩余小红包个数
     */
    private Integer stock;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 备注
     */
    private String note;

}
