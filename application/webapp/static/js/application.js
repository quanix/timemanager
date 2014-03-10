$.app = {

    /** ======================= Full Calendar 定义 ========================== **/

    initCalendar : function() {

        var calendar = $('#calendar').fullCalendar({
            // put your options and callbacks here
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'agendaDay,agendaWeek,month'
            },

            events : getCtx()+'/cal/load',

            eventDrop: function(event, delta) {
                alert("drop");
            },

            eventClick: function(event, delta) {
                alert("click");
            },

            loading:function(bool) {

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


        /**
         * 弹出新建日历表单
         * @param start
         * @param end
         */
        function openNewCalendarForm(start, end) {
            var url = getCtx()+ '/cal/new';
            //构造传递的地址
            if(start) {
                start = $.fullCalendar.formatDate(start, "yyyy-MM-dd HH:mm:ss");
                end = $.fullCalendar.formatDate(end, "yyyy-MM-dd HH:mm:ss");
                url = url + "?start=" + start + "&end=" + end;
            }
            //构造模态窗口
            $.app.modalDialog("新增日历",url,{
                width:370,
                height:430,
                ok : function(modal) {

                    return true;
                }
            });
        }

    },


    /** ============================ 模态窗口定义 ============================== **/

    modalDialog:function (title,url,settings) {

        alert(url);

        var defaultSettings = {

        };

        if(!settings) {
            settings = {};
        }
        settings = $.extend(true, {}, defaultSettings, settings);

        if(!settings.ok) {
            delete settings.buttons['确定'];
        }

        //异步加载,并下游处理
        $.ajax({
            url:url,
            headers:{table:true}
        }).done(function(data) {
           alert('done='+data);
        });
    }
}