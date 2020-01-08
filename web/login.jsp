<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户登录</title>
    <link href="css/login.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/page/header.jsp"/>
<div class="login">
    <h4 align="center">用户登录</h4>
    <form id="form1" role="form" action="login" method="post">

        <input id="username" required type="text" class="form-control" name="user.username"
               style="height: 40px; margin-top: 30px;" placeholder="请输入用户名"/>
        <s:fielderror fieldName="username"/>
        <input type="password" required class="form-control" name="user.password" style="height: 40px;margin-top: 30px;"
               placeholder="请输入密码"/>
        <s:fielderror fieldName="password"/>
        <div style="margin-top: 30px;">
            <select class="form-control" name="user.privilege">
                <option value="1">管理员</option>
                <option value="0">普通用户</option>
            </select>
        </div>
        <div style="height: 100px;width: 100%;margin-top: 10px;">
            <div style="float:left;width: 50%;padding: 20px;">
                <input type="submit" class="btn btn-primary" value="登录"
                       style="margin:auto;width: 80%;height: 50px;padding: 13px;"/>
            </div>
            <div style="float:right;width: 50%;padding: 20px;">
                <a href="register.jsp" type="button" class="btn btn-primary"
                   style="margin:auto;width: 80%;height: 50px;padding: 13px;">注册</a>
            </div>
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
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#signupForm").validate();
    });
</script>
</html>
