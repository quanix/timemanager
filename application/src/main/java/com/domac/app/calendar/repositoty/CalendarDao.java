package com.domac.app.calendar.repositoty;

import com.domac.app.calendar.entity.Calendar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Author : lihaoquan
 * Description : .日历数据访问接口
 */
public interface CalendarDao  extends CrudRepository<Calendar,String>{

    public List<Calendar> findByUserid(String userid);
}
