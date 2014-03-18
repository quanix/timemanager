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

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        return "/views/calendar/index.jsp";
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
                /**
                 * 整理结束时间
                 */
                try {

                    /**
                     * 整理开始时间
                     */
                    String tempStartTime = "00:00:00";
                    String startDate = calendar.getStartdate();
                    if(QueryUtil.isNotEmpty(calendar.getStarttime())) {
                        tempStartTime = calendar.getStarttime();
                    }

                    //标准格式 yyyy-MM-dd HH:mm:ss
                    startDate += " "+tempStartTime;

                    Date startStandardDate = DateUtils.parseDate(startDate,"yyyy-MM-dd HH:mm:ss");
                    Date endStandardDate = DateUtils.addDays(startStandardDate, calendar.getLength() - 1);
                    Date endDate = DateUtils.parseDate(calendar.getEndtime(),"HH:mm:ss");
                    boolean allDays = calendar.getStarttime() == null && calendar.getEndtime() == null;
                    if(!allDays) {
                        endStandardDate.setHours(endDate.getHours());
                        endStandardDate.setMinutes(endDate.getMinutes());
                        endStandardDate.setSeconds(endDate.getSeconds());
                    }

                    resultMap.put("id", calendar.getId());
                    resultMap.put("start", startDate);
                    resultMap.put("end", DateFormatUtils.format(endStandardDate, "yyyy-MM-dd HH:mm:ss"));
                    resultMap.put("allDay", allDays);
                    resultMap.put("title", calendar.getTitle());

                    if(StringUtils.isNotEmpty(calendar.getBackgroundcolor())) {
                        resultMap.put("backgroundColor", calendar.getBackgroundcolor());
                        resultMap.put("borderColor", calendar.getBackgroundcolor());
                    }
                    if(StringUtils.isNotEmpty(calendar.getTextcolor())) {
                        resultMap.put("textColor", calendar.getTextcolor());
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
//                logger.info("resultMap.toString()>>>>>"+resultMap.toString());
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
            @RequestParam(value = "start", required = false)String start,
            @RequestParam(value = "end", required = false)String end,Model model) {

        logger.info("cal->new->get : (start="+start+" , end="+end+")");

        Calendar calendar = new Calendar();
        calendar.setLength(1);

        try {
            Date startDate = DateUtils.parseDate(start,"yyyy-MM-dd HH:mm:ss");
            Date endDate = DateUtils.parseDate(end,"yyyy-MM-dd HH:mm:ss");
            if(start != null) {
                calendar.setStartdate(DateFormatUtils.format(startDate,"yyyy-MM-dd"));
                calendar.setLength((int)Math.ceil(1.0 * (endDate.getTime() - startDate.getTime()) / oneDayMillis));
                if(DateUtils.isSameDay(startDate, endDate)) {
                    calendar.setLength(1);
                }
                String startTimeStr  =   DateFormatUtils.format(startDate,"HH:mm:ss");
                String endTimeStr  =   DateFormatUtils.format(endDate,"HH:mm:ss");
                calendar.setStarttime(startTimeStr);
                calendar.setEndtime(endTimeStr);
            }
        }catch (Exception e) {
            e.printStackTrace();
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
    public String updateCal(@ModelAttribute("calendar") Calendar calendar) throws Exception {

        logger.info("cal->new->post");
        String flag = "0";
        System.out.println(calendar.toString());
        if(QueryUtil.isNotEmpty(calendar)
                && QueryUtil.isNotEmpty(calendar.getTitle())) {
            try {
                calendarService.update(calendar);
                flag = "1";
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewCalendar(@PathVariable("id") String id, Model model) {

        Calendar calendar = calendarService.getById(id);
        if(QueryUtil.isNotEmpty(calendar)) {
            model.addAttribute("calendar", calendar);
        }
        return "/views/calendar/viewForm.jsp";
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

    /**
     *   不懂那个时间怎么个存储，现在只有时间的长度为1结果才正确，等待quanet修改。
     * 删除主键为{id}的日历 ,{day}变化时间，目前移动的值只可以是天的整数 ,
     * @param id
     * @param day
     * @return
     */
    @RequestMapping(value ="/update/{id}/{day}", method = RequestMethod.POST)
    @ResponseBody
    public String update(@PathVariable("id") String id,@PathVariable("day") int day) {
        logger.info("is enter");
        if(day == 0) return "1";

        Calendar calendar = calendarService.getById(id);
        logger.info(calendar + "");
        String ret = "1";

        String tempStartTime = "00:00:00";
        String startDate = calendar.getStartdate();
        if(QueryUtil.isNotEmpty(calendar.getStarttime())) {
            tempStartTime = calendar.getStarttime();
        }

        //标准格式 yyyy-MM-dd HH:mm:ss
        startDate += " "+tempStartTime;

        Date startStandardDate = null;
        try {
            startStandardDate = DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss");
            Date newStartStandardDate = DateUtils.addDays(startStandardDate, day);
//            logger.info("day>>>>>"+day);
//            logger.info("old day>>>>" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startStandardDate));
//            logger.info("new day>>>>" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(newStartStandardDate));
            calendar.setStartdate(new SimpleDateFormat("yyyy-MM-dd").format(newStartStandardDate));
            calendarService.update(calendar);
        } catch (Exception e) {
            ret = "error";
            e.printStackTrace();
        }

        return ret;
    }

}
