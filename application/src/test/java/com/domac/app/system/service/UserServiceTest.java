package com.domac.app.system.service;

import com.domac.app.common.util.Digests;
import com.domac.app.common.util.Encodes;
import com.domac.app.common.util.RandomData;
import com.domac.app.system.entity.User;
import com.domac.app.testcase.TransactionalTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * @author : lihaoquan
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class UserServiceTest extends TransactionalTestCase {

    @Autowired
    private UserService userService;

    @Test
    public void testFindAllUsers() {
        List<User> users = userService.getAllUser();
        logger.info("users list size:"+users.size());
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setLoginName("lihaoquan");
        user.setUsername("quan");
        user.setPlainPassword("lihaoquan");

        userService.registerUser(user);

        System.out.println("salt:"+user.getSalt());
        System.out.println("password:"+user.getPassword());

        List<User> users = userService.getAllUser();
        logger.info("users list size:"+users.size());
    }


    @Test
    public void testEntrypt() {
        byte[] salt = Digests.generateSalt(8);
        String saltStr = Encodes.encodeHex(salt);

        byte[] hashPassword = Digests.sha1("lihaoquan".getBytes(), salt, UserService.HASH_INTERATIONS);
        String pwd = Encodes.encodeHex(hashPassword);

        System.out.println("salt:"+saltStr+" ; password:"+pwd);
    }
}
