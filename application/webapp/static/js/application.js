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

            events : ctx+'/cal/load',

            eventDrop: function(event, delta) {
                moveCalendar(event);
            },

            eventClick: function(event, delta) {
                viewCalendar(event);
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
            var url = ctx+ '/cal/new';
            //构造传递的地址
            if(start) {
                start = $.fullCalendar.formatDate(start, "yyyy-MM-dd HH:mm:ss");
                end = $.fullCalendar.formatDate(end, "yyyy-MM-dd HH:mm:ss");
                url = url + "?start=" + start + "&end=" + end;
            }
            //构造模态窗口
            $.app.modalDialog("新增计划",url,{
                width:370,
                height:430,
                ok : function(modal) {

                    var form = modal.find("#editForm");
                    if(!form.validationEngine('validate')) {
                        return false;
                    }
                    var url = ctx + "/cal/new";
                    $.post(url, form.serialize(), function() {
                        calendar.fullCalendar("refetchEvents");
                    });
                    return true;
                }
            });
        }


        /**
         * .移动日历
         * @param event
         */
        function moveCalendar(event) {
            alert('moveCalendar');
        }


        /**
         * .查看日历
         * @param event
         */
        function viewCalendar(event) {
            alert('viewCalendar');
        }

    },


    /** ============================ 模态窗口定义 ============================== **/
    _modalDialogQueue:null,

    modalDialog:function (title,url,settings) {
        var defaultSettings = {
            title : title,
            closeText : "关闭",
            closeOnEscape:false,
            height:300,
            width:600,
            modal:true,
            noTitle : false,
            close: function() {
                $(this).closest(".ui-dialog").remove();
            },
            _close : function(modal) {
                $(modal).dialog("close");
                if($.app._modalDialogQueue.length > 0) {
                    $.app._modalDialogQueue.pop();
                }
            },
            buttons:{
                '确定': function() {
                    if(settings.ok) {
                        if(settings.ok($(this))) {
                            settings._close(this);
                        }
                    } else {
                        settings._close(this);
                    }
                    if(settings.callback) {
                        settings.callback();
                    }
                },
                '关闭': function () {
                    settings._close(this);
                    if(settings.callback) {
                        settings.callback();
                    }
                }
            }
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
            var div = $("<div></div>").append(data);
            var dialog = div.dialog(settings)
                .closest(".ui-dialog").data("url", url).removeClass("ui-widget-content")
                .find(".ui-dialog-content ").removeClass("ui-widget-content").focus();

            if(settings.noTitle) {
                dialog.closest(".ui-dialog").find(".ui-dialog-titlebar").addClass("no-title");
            }
            dialog.closest(".ui-dialog").focus();
            if(!$.app._modalDialogQueue) {
                $.app._modalDialogQueue = new Array();
            }
            $.app._modalDialogQueue.push(dialog);
        });
    },

    initDatetimePicker : function() {
        //初始化 datetime picker
        $('.date:not(.custom)').each(function() {
            var $date = $(this);

            if($date.attr("initialized") == "true") {
                return;
            }

            var pickDate = $(this).find("[data-format]").data("format").toLowerCase().indexOf("yyyy-mm-dd") != -1;
            var pickTime = $(this).find("[data-format]").data("format").toLowerCase().indexOf("hh:mm:ss") != -1;
            $date.datetimepicker({
                pickDate : pickDate,
                pickTime : pickTime,
                maskInput: true,
                language:"zh-CN"
            }).on('changeDate', function(ev) {
                if(pickTime == false) {
                    $(this).data("datetimepicker").hide();
                }
            });
            $date.find(":input").click(function() {$date.find(".icon-calendar,.icon-time,.icon-date").click();});
            $date.attr("initialized", true);
        });
    }
}