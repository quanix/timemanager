package com.domac.app.support.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

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

            File attachment = generateAttachment();
            helper.addAttachment("附件信息.txt",attachment);

            mailSender.send(msg);
            logger.info("HTML版邮件已发送至quanix@163.com");
        }catch (Exception e) {
            logger.error("发送邮件失败", e);
        }
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private File generateAttachment() throws MessagingException {
        Resource resource = new FileSystemResource("E:\\maven-3.2.1\\README.txt");
        try {
            return resource.getFile();
        } catch (IOException e) {
            logger.error("构造邮件失败,附件文件不存在", e);
            throw new MessagingException("附件文件不存在", e);
        }
    }
}
