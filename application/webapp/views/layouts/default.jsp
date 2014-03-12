<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        时间管理工具
    </title>

    <link rel='stylesheet' type='text/css' href='<%=request.getContextPath() %>/static/comp/fullcalendar/fullcalendar.css' />
    <link type="text/css" href="<%=request.getContextPath() %>/static/comp/jquery-ui-bootstrap/css/bootstrap.css?2" rel="stylesheet">
    <link type="text/css" href="<%=request.getContextPath() %>/static/comp/jquery-ui-bootstrap/css/custom-theme/jquery-ui-1.10.3.custom.css?3" rel="stylesheet"/>
    <link type="text/css" href="<%=request.getContextPath() %>/static/comp/jquery-ui-bootstrap/css/font-wesome/font-awesome.min.css?1" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/comp/jquery-ui-bootstrap/css/font-wesome/font-awesome-ie7.min.css?1">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/comp/jquery-ui-bootstrap/css/custom-theme/jquery.ui.1.10.3.ie.css?1"/>
    <link href="<%=request.getContextPath() %>/static/comp/jquery-ui-bootstrap/css/layout-default-1.3.0.css?1" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/static/comp/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css?1" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/static/comp/jQuery-Validation-Engine/css/validationEngine.jquery.css?1" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/static/comp/jQuery-Validation-Engine/css/template.css?1" rel="stylesheet">

    <link href="<%=request.getContextPath() %>/static/css/application.css?1" rel="stylesheet">

    <script type='text/javascript' src='<%=request.getContextPath() %>/static/comp/jquery-ui-bootstrap/js/jquery-1.10.1.min.js?1'></script>

    <script src="<%=request.getContextPath() %>/static/comp/jquery-ui-bootstrap/js/bootstrap.min.js?1" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/static/comp/jquery-ui-bootstrap/js/jquery-ui-1.10.3.custom.min.js?1" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/static/comp/jquery-ui-bootstrap/js/jquery.layout-latest.js?1" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/static/comp/jquery-ui-bootstrap/js/jquery.blockUI.js?1" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/static/comp/jquery-ui-bootstrap/js/bootstrap.file-input.js?1" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/static/comp/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js?1" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/static/comp/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?1" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/static/comp/jQuery-Validation-Engine/js/jquery.validationEngine.js?1" charset="utf-8" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/static/comp/jQuery-Validation-Engine/js/languages/jquery.validationEngine-zh_CN.js?1" charset="utf-8" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/static/comp/nicescroll/jquery.nicescroll.min.js?3"></script>
    <script src="<%=request.getContextPath() %>/static/comp/nicescroll/jquery.nicescroll.plus.js?3"></script>

    <script type='text/javascript' src='<%=request.getContextPath() %>/static/comp/fullcalendar/fullcalendar.js'></script>
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
            width: 750px;
            margin: 0 auto;
        }
    </style>
    <script>

        function getCtx() {
            return '<%=request.getContextPath() %>';
        }

        $(document).ready(function() {
            $.app.initCalendar();//初始化日历
            $.app.initDatetimePicker();

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
