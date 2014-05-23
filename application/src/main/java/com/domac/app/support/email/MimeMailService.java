package com.domac.app.support.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * created by quanix
 *
 * MIME邮件服务类
 */
public class MimeMailService {

    private static final String DEFAULT_ENCODING = "utf-8";

    private static Logger logger = LoggerFactory.getLogger(MimeMailService.class);

    private JavaMailSender mailSender;


    /**
     * 发送MINE格式的通知消息邮件
     * @param userName
     */
    public void sendNotificationMail(String userName) {
        try {

            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg,true,DEFAULT_ENCODING);

            helper.setSubject("项目需求变更通知");
            helper.setFrom("domacode@gmail.com");
            helper.setTo("quanix@163.com");

            String content = "hello , this is ios";
            helper.setText(content);

            mailSender.send(msg);
            logger.info("HTML版邮件已发送至quanix@163.com");
        }catch (Exception e) {
            logger.error("发送邮件失败", e);
        }
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
