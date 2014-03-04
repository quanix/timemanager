<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        时间管理工具
    </title>

    <link rel='stylesheet' type='text/css' href='<%=request.getContextPath() %>/static/fullcalendar/fullcalendar.css' />
    <script type='text/javascript' src='<%=request.getContextPath() %>/static/jquery/jquery.min.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath() %>/static/fullcalendar/fullcalendar.js'></script>

    <script>
        $(document).ready(function() {
            // page is now ready, initialize the calendar...
            $('#calendar').fullCalendar({
                // put your options and callbacks here
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },

                eventDrop: function(event, delta) {
                    alert("drop");
                },

                eventClick: function(event, delta) {
                    alert("click");
                },

                editable: true,
                selectable: true,
                selectHelper: true,
                select: function(start, end, allDay) {

                }
            })
        });
    </script>
</head>
<body>
<div id='calendar'></div>
</body>
</html>
