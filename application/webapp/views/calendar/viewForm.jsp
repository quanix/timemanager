<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    #calendar div.clearfix {
        margin-top: 5px;
        padding-bottom: 5px;
        margin-left: 10px;
        clear: both;
        float: left;
    }
</style>
<div id='calendar'>
    <div class="clearfix">
        <label style="width: 60px;text-align: right;float:left;">标题：</label>
        <div style="float: left;" title="${calendar.title}">
            ${calendar.title}
        </div>
    </div>
    <div class="clearfix">
        <label style="width: 60px;text-align: right;float:left;">开始时间：</label>
        <div style="float: left;" title="${calendar.starttime}">
            ${calendar.starttime}
        </div>
    </div>
    <div class="clearfix">
        <label style="width: 60px;text-align: right;float:left;">结束时间：</label>
        <div style="float: left;" title="${calendar.endtime}">
            ${calendar.endtime}
        </div>
    </div>

    <div class="clearfix" style="margin-left: 70px;">
        <button type="submit" class="btn-delete-calendar btn btn-primary" data-id="${calendar.id}">
            <i class="icon-delete"></i>
            删除提醒事项
        </button>
    </div>
</div>
