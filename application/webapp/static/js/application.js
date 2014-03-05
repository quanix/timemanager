$.app = {

    /**
     * 初始化日历
     */
    initCalendar : function() {

        var calendar = $('#calendar').fullCalendar({
            // put your options and callbacks here
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'agendaDay,agendaWeek,month'
            },

            events : getCtx()+'/web/cal/load',

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
                openNewCalendarForm(start,end);
                calendar.fullCalendar('unselect');
            }
        });

        $('span.fc-button-prev')
            .before('<span class="fc-button fc-button-add fc-state-default fc-corner-left fc-corner-right">' +
                '新增</span>');

        $(".fc-button-add").click(function() {
            openNewCalendarForm();
        });


        function openNewCalendarForm(start, end) {
             alert('start:'+start+",end:"+end);
        }

    }
}