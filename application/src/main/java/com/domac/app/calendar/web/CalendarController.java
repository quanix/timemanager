package com.domac.app.calendar.web;

import com.domac.app.calendar.entity.Calendar;
import com.domac.app.calendar.service.CalendarService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = {"/{index:index;?.*}"})
    public String homepage() {
        logger.info("登陆日历管理首页");
        return "/views/layouts/default.jsp";
    }


    /**
     * .异步加载
     * @return
     */
    @RequestMapping("/load")
    @ResponseBody
    public List<Map> ajaxLoad() {

        System.out.println("zsdsdhsajdjasdjhasjdhj");

        List<Map> calendars = Lists.newArrayList();
        try {
            Map<String,Object> map = Maps.newHashMap();

            map.put("id", "1234234234");
            map.put("start", "2014-03-06 20:00:00");
            map.put("end", "2014-03-06 23:30:00");
            map.put("title", "TYYSDS");
            map.put("allDay", false);

            calendars.add(map);
        }catch (Exception e) {
            e.printStackTrace();
        }
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

}
