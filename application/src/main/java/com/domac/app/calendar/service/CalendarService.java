package com.domac.app.calendar.service;

import com.domac.app.calendar.entity.Calendar;
import com.domac.app.calendar.repositoty.CalendarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : lihaoquan
 *
 * 日历操作服务类
 */
@Component
@Transactional
public class CalendarService {

    @Autowired
    private CalendarDao calendarDao;

    public List<Calendar> findByUserid(String userid) throws Exception {
        return calendarDao.findByUserid(userid);
    }

    /**
     * .保存与更新
     * @param calendar
     */
    public void update(Calendar calendar) throws Exception {
        calendarDao.save(calendar);
    }


    /**
     * .获取所有对象
     * @return
     */
    public List<Calendar> getAll() throws Exception {
        return (List<Calendar>)calendarDao.findAll();
    }
}
