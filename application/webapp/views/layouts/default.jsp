<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        时间管理工具
    </title>

    <link rel='stylesheet' type='text/css' href='<%=request.getContextPath() %>/static/fullcalendar/fullcalendar.css' />
    <link rel='stylesheet' type='text/css' href='<%=request.getContextPath() %>/static/jquery-ui/css/custom-theme/jquery-ui-1.10.4.custom.css ' />
    <script type='text/javascript' src='<%=request.getContextPath() %>/static/jquery/jquery-1.10.1.min.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath() %>/static/jquery-ui/js/jquery-ui-1.10.4.custom.min.js'></script>
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
            width: 800px;
            margin: 0 auto;
        }
    </style>
    <script>

        function getCtx() {
            return '<%=request.getContextPath() %>';
        }

        $(document).ready(function() {
            $.app.initCalendar();//初始化日历

            $( "#dialog" ).dialog({
                autoOpen: false,
                height: 200,
                width: 450,
                modal: true,
                hide: {
                    effect: "explode",
                    duration: 1000
                }
            });

            $( "#create-user" ).button().click(function() {
                $( "#dialog" ).dialog( "open" );
            });
        });
    </script>
</head>
<body>

<!-- 说明按钮 -->
<button id="create-user">项目说明</button>

<!-- 日历展示层 -->
<div id='calendar'></div>

<!-- 项目说明 -->
<div id="dialog" title="项目地址">
    <p>GitHub:</p>
    https://github.com/quanet/timemanager
</div>
</body>
</html>
