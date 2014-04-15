package com.domac.app.system.service;

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
}
