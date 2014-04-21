package com.domac.app.system.entity;

import com.domac.app.common.entity.AbstractEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author : lihaoquan
 */
@Entity
@Table(name = "s_user_role")
public class UserRole extends AbstractEntity {

    private String userid;
    private String roleid;

    @NotBlank
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @NotBlank
    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
}
