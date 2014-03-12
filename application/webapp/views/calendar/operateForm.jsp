<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    #editForm div.clearfix {
        margin-top: 10px;
        padding-bottom: 5px;
        margin-left: 10px;
        clear: both;
        float: left;
    }
</style>
<div class="clearfix">
    <form:form id="editForm" method="post" commandName="model" cssClass="row-fluid form-horizontal form-inline">

        <div class="clearfix">
            <form:label path="title" cssStyle="width: 60px;text-align: right;">标题：</form:label>
            <form:input path="title" cssClass="validate[required,maxSize[100]]"/>
        </div>

        <div class="clearfix">
            <form:label path="startdate"  cssStyle="width: 60px;text-align: right;">开始日期：</form:label>
            <span class="input-append date">
                <form:input path="startdate" cssClass="validate[required]" cssStyle="width: 180px;"
                            data-position="bottom-left" data-format="yyyy-MM-dd"/>
                <span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
            </span>
        </div>

        <div class="clearfix">
            <form:label path="length"  cssStyle="width: 60px;text-align: right;">持续天数：</form:label>
            <form:input path="length" cssClass="validate[required] input-small"/>
            <label><input class="all-day" type="checkbox">全天提醒事项</label>
        </div>


        <div class="clearfix">
            <form:label path="backgroundcolor" cssStyle="width: 60px;text-align: right;">背景颜色：</form:label>
            <select id="backgroundcolor" name="backgroundcolor" style="background: ${backgroundcolorList[0]}">
                <c:forEach items="${backgroundColorList}" var="c">
                    <option style="background: ${c}" value="${c}">&nbsp;</option>
                </c:forEach>
            </select>
        </div>

   </form:form>
</div>
<script>
    $(function() {

        $("#backgroundcolor").change(function() {
            $(this).attr("style", $(this).find("option:selected").attr("style"));
        })

        $(".all-day").change(function() {
            if($(this).is(":checked")) {
                $("[name=startTime],[name=endTime]").val("").attr("disabled", true).removeClass("validate[required]");
            } else {
                $("[name=startTime],[name=endTime]").removeAttr("disabled").addClass("validate[required]");
            }
        });
    })
</script>
