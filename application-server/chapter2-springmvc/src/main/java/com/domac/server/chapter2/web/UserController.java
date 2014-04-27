package com.domac.server.chapter2.web;

import com.domac.server.chapter2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : lihaoquan
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public String hello(Model model) {
        userService.sayHello();
        model.addAttribute("msg", "hello world");
        return "user/hello";
    }
}
