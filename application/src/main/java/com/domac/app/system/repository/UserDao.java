package com.domac.app.system.repository;

import com.domac.app.system.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : lihaoquan
 */
public interface UserDao extends CrudRepository<User,String> {
    public User findByLoginName(String loginName);
}
