package com.domac.app.support.email;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

/**
 * created by quanix
 *
 * 简单文本邮件服务类
 */
public class SimpleMailService {

    private static Logger logger = LoggerFactory.getLogger(SimpleMailService.class);

    private JavaMailSender mailSender;

    private String textTemplate;


    /**
     * 发送文本通知邮件
     * @param userName
     */
    public void sendNotificationMail(String userName) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("domacode@gmail.com");
        msg.setTo("quanix@163.com");
        msg.setSubject("系统消息通知");

        // 将用户名与当期日期格式化到邮件内容的字符串模板
        String content = String.format(textTemplate, userName, new Date());
        msg.setText(content);

        try {
            mailSender.send(msg);
            if (logger.isInfoEnabled()) {
                logger.info("纯文本邮件已发送至{}", StringUtils.join(msg.getTo(), ","));
            }
        } catch (Exception e) {
            logger.error("发送邮件失败", e);
        }
    }

    public void setTextTemplate(String textTemplate) {
        this.textTemplate = textTemplate;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
