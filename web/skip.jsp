<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/15
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";  //获取web应用根目录路径
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String target = basePath + "register.jsp";
    response.setHeader("refresh", "5;URL=" + target);
%>
邮件已经发送至指定邮箱，请登陆邮箱进行验证. <br>
</body>
</html>
