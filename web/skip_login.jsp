<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/15
  Time: 16:42
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
    //页面每隔5秒自动链接至另一页面
    response.setHeader("refresh", "5;URL=http://www.baidu.com");
%>
${message}
<br>
<input type="button" value="按钮" onclick="javascrtpt:window.location.href='http://www.baidu.com'">
</body>
</html>
