package com.domac.app.calendar.web;

import com.domac.app.calendar.entity.Calendar;
import com.domac.app.calendar.service.CalendarService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author : lihaoquan
 */
@Controller
@RequestMapping("/cal")
public class CalendarController {

    private static Logger logger = LoggerFactory.getLogger(CalendarController.class);

    @Autowired
    private CalendarService calendarService;

    /**
     * 登陆日历管理首页
     * @return
     */
    @RequestMapping("/index")
    public String homepage() {
        //testCal();
        //logger.info("登陆日历管理首页");
        return "/views/layouts/default.jsp";
    }


    /**
     * .异步加载
     * @return
     */
    @RequestMapping("/load")
    @ResponseBody
    public List<Calendar> ajaxLoad() {
        logger.info("异步载入日历数据");
        List<Calendar> calendars = Lists.newArrayList();
        return calendars;
    }

    /**
     * 創建新的日曆
     * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String initNewCal() {
        return null;
    }

    /**
     * 更新日曆
     * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    @ResponseBody
    public String updateCal() {
        return null;
    }


    /**
     * .测试日历CRUD
     */
    public void testCal() {

        Calendar calendar = new Calendar();
        calendar.setUserid("admin");
        calendar.setTitle(UUID.randomUUID().toString()+"_xxxxxxx");

        calendarService.update(calendar);

        List<Calendar> calendarList = calendarService.findByUserid("admin");
        System.out.println("----- calendar table size :"+calendarList.size());
    }
}
