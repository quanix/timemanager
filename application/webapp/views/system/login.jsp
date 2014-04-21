<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">

    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />

    <title>登陆页</title>
    <link rel='stylesheet' type='text/css' href='${ctx}/static/comp/fullcalendar/fullcalendar.css' />
    <link type="text/css" href="${ctx}/static/comp/jquery-ui-bootstrap/css/bootstrap.css?2" rel="stylesheet">
    <link type="text/css" href="${ctx}/static/comp/jquery-ui-bootstrap/css/custom-theme/jquery-ui-1.10.3.custom.css?3" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/static/comp/jquery-ui-bootstrap/css/font-wesome/font-awesome.min.css?1" rel="stylesheet"/>
    <link rel="stylesheet" href="${ctx}/static/comp/jquery-ui-bootstrap/css/font-wesome/font-awesome-ie7.min.css?1">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/comp/jquery-ui-bootstrap/css/custom-theme/jquery.ui.1.10.3.ie.css?1"/>
    <link href="${ctx}/static/comp/jquery-ui-bootstrap/css/layout-default-1.3.0.css?1" rel="stylesheet">
    <link href="${ctx}/static/comp/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css?1" rel="stylesheet">
    <link href="${ctx}/static/comp/jQuery-Validation-Engine/css/validationEngine.jquery.css?1" rel="stylesheet">
    <link href="${ctx}/static/comp/jQuery-Validation-Engine/css/template.css?1" rel="stylesheet">

    <link href="${ctx}/static/css/application.css?1" rel="stylesheet">

    <script type='text/javascript' src='${ctx}/static/comp/jquery-ui-bootstrap/js/jquery-1.10.1.min.js?1'></script>

    <script src="${ctx}/static/comp/jquery-ui-bootstrap/js/bootstrap.min.js?1" type="text/javascript"></script>
    <script src="${ctx}/static/comp/jquery-ui-bootstrap/js/jquery-ui-1.10.3.custom.min.js?1" type="text/javascript"></script>
    <script src="${ctx}/static/comp/jquery-ui-bootstrap/js/jquery.layout-latest.js?1" type="text/javascript"></script>
    <script src="${ctx}/static/comp/jquery-ui-bootstrap/js/jquery.blockUI.js?1" type="text/javascript"></script>
    <script src="${ctx}/static/comp/jquery-ui-bootstrap/js/bootstrap.file-input.js?1" type="text/javascript"></script>
    <script src="${ctx}/static/comp/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js?1" type="text/javascript"></script>
    <script src="${ctx}/static/comp/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?1" type="text/javascript"></script>
    <script src="${ctx}/static/comp/jQuery-Validation-Engine/js/jquery.validationEngine.js?1" charset="utf-8" type="text/javascript"></script>
    <script src="${ctx}/static/comp/jQuery-Validation-Engine/js/languages/jquery.validationEngine-zh_CN.js?1" charset="utf-8" type="text/javascript"></script>
    <script src="${ctx}/static/comp/nicescroll/jquery.nicescroll.min.js?3"></script>
    <script src="${ctx}/static/comp/nicescroll/jquery.nicescroll.plus.js?3"></script>

    <script type='text/javascript' src='${ctx}/static/comp/fullcalendar/fullcalendar.js'></script>
    <script type="text/javascript" src="${ctx}/static/js/application.js"></script>
</head>
<body>

    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <a class="brand" href="#">&nbsp;&nbsp;时间管理工具</a>
            <ul class="nav">
                <li><a href="http://www.google.com" target="_blank">Google</a></li>
                <li class="active"><a href="#">登录</a></li>
                <li><a href="https://github.com/quanet" target="_blank">github</a></li>
            </ul>
            <a class="brand" style="float: right" href="domacode@gmail.com" target="_blank">&nbsp;&nbsp;问题反馈</a>
        </div>
    </div>

    <div class="container">
        <div class="login">
            <div class="title">用户登录</div>
            <div class="form">

                <div style="margin-right: 30px;">
                    <es:showMessage></es:showMessage>
                </div>

                <form id="loginForm" method="post" class="form-horizontal">
                    <div class="control-group">
                        <label for="username">用户名、邮箱或手机号</label>
                        <div class="input-prepend">
                            <span class="add-on icon-user"></span>
                            <input type="text" id="username" name="username" value="${param.username}"
                                   class="input-xlarge validate[required]" placeholder="请输入用户名、邮箱或手机号">
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="password">密码</label>
                        <div class="input-prepend">
                            <span class="add-on icon-key"></span>
                            <input type="password" id="password" name="password"
                                   class="input-xlarge validate[required]" placeholder="请输入密码">
                        </div>
                    </div>
                    <%-- jcaptchaEbabled 在JCaptchaValidateFilter设置 --%>
                    <c:if test="${jcaptchaEbabled}">
                        <div class="control-group">
                            <label for="jcaptchaCode">验证码</label>
                            <div class="input-prepend">
                                <span class="add-on icon-circle-blank"></span>
                                <input type="text" id="jcaptchaCode" name="jcaptchaCode"
                                       class="input-medium validate[required,ajax[ajaxJcaptchaCall]]" placeholder="请输入验证码">
                            </div>
                            <img class="jcaptcha-btn jcaptcha-img" style="margin-left: 10px;" src="${ctx}/jcaptcha.jpg" title="点击更换验证码">
                            <a class="jcaptcha-btn btn btn-link">换一张</a>
                        </div>
                    </c:if>

                    <div class="control-group">
                        <label class="checkbox remember"><input type="checkbox" name="rememberMe" value="true">下次自动登录</label>
                        <input id="submitForm" type="submit" class="btn btn-login pull-left" value="登录">
                        <input id="registerForm" type="button" class="btn btn-success pull-left" style="margin-left: 20px;" value="注册">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function() {
            $("#username").focus();
            $(".jcaptcha-btn").click(function() {
                var img = $(".jcaptcha-img");
                var imageSrc = img.attr("src");
                if(imageSrc.indexOf("?") > 0) {
                    imageSrc = imageSrc.substr(0, imageSrc.indexOf("?"));
                }
                imageSrc = imageSrc + "?" + new Date().getTime();
                img.attr("src", imageSrc);
            });
            $.validationEngineLanguage.allRules.ajaxJcaptchaCall={
                "url": "${ctx}/jcaptcha-validate",
                "alertTextLoad": "* 正在验证，请稍等。。。"
            };
            $("#loginForm").validationEngine({scroll:false});
        });
    </script>
</body>
</html>
