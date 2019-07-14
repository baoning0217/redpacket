package com.xishanqu.redpacket.common.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @Function: 发送邮件
 * @Author: BaoNing
 * @Date: 2019-07-14
 */
@Component
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送邮件
     * @param from 发件人
     * @param to 收件人
     * @param cc 抄送人
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleMail(String from, String to, String cc, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setCc(cc);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }



}
