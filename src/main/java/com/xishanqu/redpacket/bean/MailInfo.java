package com.xishanqu.redpacket.bean;

import lombok.Data;

/**
 * @Function:
 * @Author: BaoNing
 * @Date: 2019-07-14
 */
@Data
public class MailInfo {

    /**
     * 发件人
     */
    private String from = "captainbn@qq.com";

    /**
     * 收件人
     */
    private String to = "bnwyyx217@163.com";

    /**
     * 抄送人
     */
    private String cc = "634582886@qq.com";

    /**
     * 主题
     */
    private String subject = "红包";

    /**
     * 内容
     */
    private String content = "新增一个大红包";


}
