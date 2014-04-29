package com.domac.app.calendar.repository;

import com.domac.app.system.entity.User;
import com.domac.app.system.repository.UserMyBatisDao;
import com.domac.app.testcase.TransactionalTestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * @author : lihaoquan
 */
@DirtiesContext
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class UserMyBatisDaoTest extends TransactionalTestCase{

    private static Logger logger = LoggerFactory.getLogger(UserMyBatisDaoTest.class);

    @Autowired
    private UserMyBatisDao userMyBatisDao;

    @Test
    public void getAllUser() {
        List<User> userList =  userMyBatisDao.getAllUsers();

        System.out.println("mybatis test user list size = "+userList.size());

    }
}
