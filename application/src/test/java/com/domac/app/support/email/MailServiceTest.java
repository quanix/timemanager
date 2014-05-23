package com.domac.app.support.email;

import com.domac.app.testcase.TransactionalTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * created by quanix
 */

@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml", "/applicationContext-email.xml" })
public class MailServiceTest extends TransactionalTestCase {


    @Autowired
    private SimpleMailService simpleMailService;

    @Autowired
    private MimeMailService mimeMailService;

    @Test
    public void sendSimpleMail() throws MessagingException, InterruptedException, IOException {
        simpleMailService.sendNotificationMail("domac");
    }


    @Test
    public void sendMimeMail() throws InterruptedException, MessagingException, IOException {

        mimeMailService.sendNotificationMail("idomac");
    }
}
