package com.xishanqu.redpacket.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-07-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageInfo {

    private String name;

    private String content;
}
