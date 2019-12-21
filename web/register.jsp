<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/13
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";  //获取web应用根目录路径
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户注册</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <!--<link rel="stylesheet" type="text/css" href="styles.css">-->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/register.css" rel="stylesheet">
</head>
<body>
<div class="register">
    <h4 align="center">用户注册</h4>
    <form id="form1" role="form" action="<%=basePath%>register.action" method="post">
        <%--<label for="username">用户名：</label>--%>
        <input id="username" type="text" class="form-control" name="username" style="height: 40px;margin-top: 20px; "
               placeholder="请输入用户名"><s:fielderror fieldName="username"/>
        <%--<label for="password">密码：</label>--%>
        <input id="password" type="password" class="form-control" name="password" style="height: 40px;margin-top: 20px;"
               placeholder="请输入密码"><s:fielderror fieldName="password"/>

        <%--<label for="confirm_password">确认密码：</label>--%>
        <input id="repassword" type="password" class="form-control" name="repassword"
               style="height: 40px;margin-top: 20px;"
               placeholder="请重复密码"><s:fielderror fieldName="repassword"/>

        <div style="margin-top: 10px;margin-left: 10px;">
            <div style="float: left;margin-bottom: 10px">性别:</div>
            <div style="float:left;margin-left: 100px;margin-bottom: 10px">
                <label><input type="radio" name="sex" value="男">男</label>
            </div>
            <div style="float: right;margin-right: 100px;margin-bottom: 10px">
                <label><input type="radio" name="sex" value="女">女</label>
            </div>
        </div>
        <%--<label for="email">请输入邮箱：</label>--%>
        <input id="email" type="email" class="form-control" name="email" style="height: 40px;margin-top: 20px;"
               placeholder="请输入邮箱"><s:fielderror fieldName="email"/>
        <div style="height: 80px;width: 100%;margin-top: 20px;margin-left: 30px;">
            <!--<div style="float:left;width: 100%;padding: 20px;">-->
            <input type="submit" class="btn btn-primary" value="<s:property value='%{getText("register.button")}'/>"
                   style="margin:auto;width: 80%;height: 40px;padding: 10px;"/>
            <!--</div>-->
        </div>
    </form>
</div>
<div class="bottom"
     style="position:absolute;bottom:0px;margin-top: 20px;background-color: rgba(0,0,0,0.8);width:100%;height: 100px;color: darkgray">
    <div style="width: 400px;padding-top: 35px;padding-left:40px;padding-right: 40px;margin:auto;">
        <div>
            友情链接：
            <a href="https://github.com/tony-coder" style="color: darkgray">&nbsp;github&nbsp;|&nbsp;</a>
            <a href="http://www.csdn.net/" style="color: darkgray">csdn&nbsp;|&nbsp;</a>
            <a href="http://www.oschina.net/" style="color: darkgray">开源中国&nbsp;|&nbsp;</a>
            <a href="http://stackoverflow.com/" style="color: darkgray">stackflow</a><br>
            小组成员：鲍锋雄、程凯、王梦欣
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript">
    // JS validate校验框架的使用
    $(function () {
        $("#form1").validate({
            rules: {
                username: {
                    required: true
                },
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    rangelength: [6, 20]
                },
                repassword: {
                    required: true,
                    equalTo: "#password"
                }
            },
            messages: {
                username: {
                    required: "必填"
                },
                email: {
                    required: "必填",
                    email: "E-Mail格式不正确"
                },
                password: {
                    required: "不能为空",
                    rangelength: $.validator.format("密码最小长度:{0}, 最大长度:{1}。")
                },
                repassword: {
                    required: "不能为空",
                    equalTo: "两次密码输入不一致"
                }
            }
        });
    });
</script>
<style type="text/css">
    .error {
        color: red;
    }
</style>
</html>
