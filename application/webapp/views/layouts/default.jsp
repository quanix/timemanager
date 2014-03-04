<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        时间管理工具
    </title>

    <link rel='stylesheet' type='text/css' href='<%=request.getContextPath() %>/static/fullcalendar/fullcalendar.css' />
    <script type='text/javascript' src='<%=request.getContextPath() %>/static/jquery/jquery.min.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath() %>/static/fullcalendar/fullcalendar.js'></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/application.js"></script>
    <style>
        legend {
            cursor: pointer;
        }
        .fc-button-add {
            margin-right: 10px!important;
        }

        #loading {
            position: absolute;
            top: 5px;
            right: 5px;
        }

        .ui-dialog {
            overflow: visible!important;
        }
        .ui-dialog-content {
            overflow: visible!important;
        }

        #calendar {
            width: 1000px;
            margin: 0 auto;
        }
    </style>
    <script>
        $(document).ready(function() {
            $.app.initCalendar();//初始化日历
        });
    </script>
</head>
<body>
<div id='calendar'></div>
</body>
</html>
