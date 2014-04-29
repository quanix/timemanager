package com.domac.app.system.repository;

import com.domac.app.system.entity.User;

import java.util.List;

/**
 * @author : lihaoquan
 */
@MyBatisRepository
public interface UserMyBatisDao {

      List<User> getAllUsers();
}
