package com.domac.app.calendar.repositoty;

import com.domac.app.calendar.entity.Calendar;
import com.domac.app.testcase.TransactionalTestCase;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * Author : lihaoquan
 * Description : .日历数据访问层的单元测试
 */
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class CalendarDaoTest extends TransactionalTestCase {

    private static Logger logger = LoggerFactory.getLogger(CalendarDaoTest.class);

    @Autowired
    private CalendarDao calendarDao;

    @Before
    public void insert() {
        System.out.println("--------------");
        //calendarDao.deleteAll();

    }


    //@Test
    public void getCalsByUserIds() {

        List<Calendar> calendars = calendarDao.findByUserid("admin");
        for(Calendar calendar : calendars) {
            logger.info("title:"+calendar.getTitle());
        }
    }

    @Test
    public void getAll() {
        List<Calendar> calendars = (List<Calendar>) calendarDao.findAll();
        for(Calendar calendar : calendars) {
            logger.info(calendar.toString());
        }
    }

}
