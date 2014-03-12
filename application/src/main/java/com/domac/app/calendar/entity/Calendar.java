package com.domac.app.calendar.entity;

import com.domac.app.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author : lihaoquan
 *
 * 日历对象
 */
@Entity
@Table(name = "t_app_calendar")
public class Calendar extends AbstractEntity {

    /**
     * 所有人
     */
    private String userid;

    /**
     * 标题
     */
    private String title;

    /**
     * 详细信息
     */
    private String details;

    /**
     * 开始日期
     */
    private Date startdate;

    /**
     * 持续时候
     */
    private Integer length;

    /**
     * 开始时间
     */
    private Date starttime;

    /**
     * 结束时间
     */
    private Date endtime;

    /**
     * 背景颜色
     */
    private String backgroundcolor;

    /**
     * 字体颜色
     */
    private String textcolor;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getBackgroundcolor() {
        return backgroundcolor;
    }

    public void setBackgroundcolor(String backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
    }

    public String getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(String textcolor) {
        this.textcolor = textcolor;
    }

    @Override
    public String toString() {
        //return super.toString();
        StringBuffer sb = new StringBuffer();
        sb.append("userid = "+userid+"\n");
        sb.append("title = "+title+"\n");
        sb.append("details = "+details+"\n");
        sb.append("startdate = "+startdate+"\n");
        sb.append("starttime = "+starttime+"\n");
        sb.append("backgroundcolor = "+backgroundcolor+"\n");
        sb.append("textcolor = "+textcolor+"\n");
        return sb.toString();
    }
}
