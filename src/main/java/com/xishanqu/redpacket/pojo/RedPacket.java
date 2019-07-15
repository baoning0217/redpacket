package com.xishanqu.redpacket.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author BaoNing 2019/7/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "t_red_packet")
@ApiModel(value = "红包实体类", description = "红包信息描述")
public class RedPacket implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 红包编号
     */
    @ApiModelProperty(value = "红包主键")
    private Long id;

    /**
     * 发红包用户
     */
    @ApiModelProperty(value = "用户id")
    @NotNull(message = "{redpacket.userId.notnull}")
    private Long userId;

    /**
     * 红包金额
     */
    @ApiModelProperty(value = "红包金额" )
    private Double amount;

    /**
     * 发红包时间
     */
    @ApiModelProperty(value = "发红包时间")
    private Timestamp sendDate;

    /**
     * 小红包总数
     */
    @ApiModelProperty(value = "小红包总数")
    private Integer total;

    /**
     * 单个小红包金额
     */
    @ApiModelProperty(value = "单个小红包金额")
    private Double unitAmount;

    /**
     * 剩余小红包个数
     */
    @ApiModelProperty(value = "剩余小红包个数")
    private Integer stock;

    /**
     * 版本
     */
    @ApiModelProperty(value = "版本")
    private Integer version;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

}
