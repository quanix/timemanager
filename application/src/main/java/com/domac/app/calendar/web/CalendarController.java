package com.domac.app.calendar.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : lihaoquan
 */
@Controller
@RequestMapping("/cal")
public class CalendarController {

    private static Logger logger = LoggerFactory.getLogger(CalendarController.class);


    /**
     * 登陆日历管理首页
     * @return
     */
    @RequestMapping("/index")
    public String homepage() {
        logger.info("登陆日历管理首页");
        return "/views/layouts/default.jsp";
    }
}
