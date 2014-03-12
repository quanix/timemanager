package com.domac.app.calendar.web;

import com.domac.app.calendar.entity.Calendar;
import com.domac.app.calendar.service.CalendarService;
import com.domac.app.common.util.QueryUtil;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    private static final long oneDayMillis = 24L * 60 * 60 * 1000;
    private static final String dataFormat = "yyyy-MM-dd HH:mm:ss";
    private static List<String> backgroundColorList = Lists.newArrayList();

    static {

        backgroundColorList.add("#3a87ad");
        backgroundColorList.add("#0d7813");
        backgroundColorList.add("#f2a640");
        backgroundColorList.add("#b373b3");
        backgroundColorList.add("#f2a640");
        backgroundColorList.add("#668cb3");
        backgroundColorList.add("#28754e");
        backgroundColorList.add("#8c66d9");

    }


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
    public List<Map> ajaxLoad() throws Exception{
        /**
         * 把日历列表转换为适当的对象列表
         */
        List<Calendar> calendarLists = calendarService.getAll();
        return Lists.<Calendar,Map>transform(calendarLists,new Function<Calendar, Map>() {
            @Override
            public Map apply(Calendar calendar) {
                Map resultMap = Maps.newHashMap();

                Date startDate = new Date(calendar.getStartdate().getTime());
                Date endDate = DateUtils.addDays(startDate, calendar.getLength() - 1);
                boolean allDays = calendar.getStarttime() == null && calendar.getEndtime() == null;
                if(!allDays) {
                    startDate.setHours(calendar.getStarttime().getHours());
                    startDate.setMinutes(calendar.getStarttime().getMinutes());
                    startDate.setSeconds(calendar.getStarttime().getSeconds());
                    endDate.setHours(calendar.getEndtime().getHours());
                    endDate.setMinutes(calendar.getEndtime().getMinutes());
                    endDate.setSeconds(calendar.getEndtime().getSeconds());
                }
                resultMap.put("id", calendar.getId());
                resultMap.put("start", DateFormatUtils.format(startDate, "yyyy-MM-dd HH:mm:ss"));
                resultMap.put("end", DateFormatUtils.format(endDate, "yyyy-MM-dd HH:mm:ss"));
                resultMap.put("allDay", allDays);
                resultMap.put("title", calendar.getTitle());
                resultMap.put("details", calendar.getDetails());
                if(StringUtils.isNotEmpty(calendar.getBackgroundcolor())) {
                    resultMap.put("backgroundColor", calendar.getBackgroundcolor());
                    resultMap.put("borderColor", calendar.getBackgroundcolor());
                }
                if(StringUtils.isNotEmpty(calendar.getTextcolor())) {
                    resultMap.put("textColor", calendar.getTextcolor());
                }
                return resultMap;
            }
        });
    }

    /**
     * 創建新的日曆
     * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String initNewCal(
            @RequestParam(value = "start", required = false)
            @DateTimeFormat(pattern = dataFormat) Date start,
            @RequestParam(value = "end", required = false)
            @DateTimeFormat(pattern = dataFormat) Date end,Model model) {

        logger.info("cal->new->get");

        Calendar calendar = new Calendar();
        calendar.setLength(1);
        if(start != null) {
            calendar.setStartdate(start);
            calendar.setLength((int)Math.ceil(1.0 * (end.getTime() - start.getTime()) / oneDayMillis));
            if(DateUtils.isSameDay(start, end)) {
                calendar.setLength(1);
            }
            if(!"00:00:00".equals(DateFormatUtils.format(start, "HH:mm:ss"))) {
                calendar.setStarttime(start);
            }
            if(!"00:00:00".equals(DateFormatUtils.format(end, "HH:mm:ss"))) {
                calendar.setEndtime(end);
            }
        }
        model.addAttribute("backgroundColorList", backgroundColorList);
        model.addAttribute("model", calendar);


        return "/views/calendar/operateForm.jsp";
    }

    /**
     * 更新日曆
     * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    @ResponseBody
    public String updateCal(@ModelAttribute("calendar") Calendar calendar) {

        logger.info("cal->new->post");
        System.out.println(calendar.toString());

        if(QueryUtil.isNotEmpty(calendar)
                && QueryUtil.isNotEmpty(calendar.getTitle())) {
            try {
                //calendarService.update(calendar);
                return "1";
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "hello calendar";
    }


    /**
     * 删除主键为{id}的日历
     * @param id
     * @return
     */
    @RequestMapping(value ="/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCal(String id) {
        calendarService.delete(id);
        return "1";
    }

}
