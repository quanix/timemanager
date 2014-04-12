package com.domac.app.system.entity;

import com.domac.app.common.entity.AbstractEntity;

/**
 * @author : lihaoquan
 */
public class User extends AbstractEntity {

    private String loginName;
    private String name;
    private String plainPassword;
    private String password;
    private String salt;
    private String roles;
    private String registerDate;

    public User() {}

    public User(String id) {
        this.id = id;
    }



}
