package com.domac.app.system.service;

import com.domac.app.system.entity.User;
import com.domac.app.system.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author : lihaoquan
 * 用户操作服务类
 */
@Component
@Transactional
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    /**
     * 获取所有用户
     * @return
     */
    public List<User> getAllUser() {
        return (List<User>) userDao.findAll();
    }
}
