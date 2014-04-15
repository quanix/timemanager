package com.domac.app.system.service;

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
        user.setLoginName(RandomData.randomName("user"));
        user.setUsername(RandomData.randomName("User"));
        user.setPlainPassword(RandomData.randomName("password"));

        userService.registerUser(user);

        System.out.println("salt:"+user.getSalt());

        List<User> users = userService.getAllUser();
        logger.info("users list size:"+users.size());
    }
}
