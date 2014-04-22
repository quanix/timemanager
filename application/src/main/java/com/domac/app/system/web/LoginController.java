package com.domac.app.system.web;

import com.domac.app.common.util.QueryUtil;
import com.domac.app.system.service.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.Query;

/**
 * @author : lihaoquan
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 登陆到前台首页
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        return "/views/system/login.jsp";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
        Subject subject = SecurityUtils.getSubject();

        /*if(QueryUtil.isNotEmpty(subject)
                && QueryUtil.isNotEmpty(subject.getPrincipals())) {
            return "/views/calendar/index.jsp";
        }*/

        if(QueryUtil.isNotEmpty(subject) && subject.isAuthenticated()) {
            return "/views/calendar/index.jsp";
        }
        return "/views/system/login.jsp";
    }
}
