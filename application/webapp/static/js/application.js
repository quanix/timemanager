$.app = {

    initCalendar : function() {

        var calendar = $('#calendar').fullCalendar({
            // put your options and callbacks here
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'agendaDay,agendaWeek,month'
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
                calendar.fullCalendar('unselect');
            }
        });

        $('span.fc-button-prev')
            .before('<span class="fc-button fc-button-add fc-state-default fc-corner-left fc-corner-right">' +
                '新增</span>');

        $(".fc-button-add").click(function() {
            alert('创建测试');
        });

    }
}