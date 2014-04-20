package com.domac.app.system.service;

import com.domac.app.common.util.Clock;
import com.domac.app.common.util.Digests;
import com.domac.app.common.util.Encodes;
import com.domac.app.system.entity.User;
import com.domac.app.system.repository.UserDao;
import org.apache.commons.lang3.StringUtils;
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

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

    private Clock clock = Clock.DEFAULT;

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     * @param user
     */
    private void entryptPassword(User user) {
         byte[] salt = Digests.generateSalt(SALT_SIZE);
         user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

    /**
     * 获取所有用户
     * @return
     */
    public List<User> getAllUser() {
        return (List<User>) userDao.findAll();
    }


    /**
     * 保存与更新用户
     * @param user
     */
    public void  registerUser(User user) {
        entryptPassword(user);
        user.setRegisterDate(clock.getCurrentDate());
        userDao.save(user);
    }

    /**
     * 保存与更新用户
     * @param user
     */
    public void  updateUser(User user) {
        if (StringUtils.isNotBlank(user.getPlainPassword())) {
            entryptPassword(user);
        }
        userDao.save(user);
    }

    /**
     * 根据登陆名称查找用户
     * @param loginName
     * @return
     */
    public User findUserByLoginName(String loginName) {
        return userDao.findByLoginName(loginName);
    }
}
