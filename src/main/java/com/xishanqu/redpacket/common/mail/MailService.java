package com.xishanqu.redpacket.common.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;

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
     * 发送简单邮件
     *
     * @param from    发件人
     * @param to      收件人
     * @param cc      抄送人
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleMail(String from, String to, String cc, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setCc(cc);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }

    /**
     * 发送带附件邮件
     *
     * @param from
     * @param to
     * @param cc
     * @param subject
     * @param content
     * @param file
     */
    public void sendAttachFileMail(String from, String to, String cc, String subject, String content, MultipartFile file) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setCc(cc);
            messageHelper.setSubject(subject);
            messageHelper.setText(content);
            messageHelper.addAttachment(file.getName(), file);
            javaMailSender.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
