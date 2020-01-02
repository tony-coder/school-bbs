<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2020/1/2
  Time: 1:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
      <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
       -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/titlebar.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <script type="text/javascript">
        function spanSubmit(){
            document.fileForm.submit();
        }
    </script>
</head>

<body>


<nav class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">BBS技术论坛</a>
    </div>

    <div>
        <ul class="nav navbar-nav">
            <li><a href="<%=path%>/index.jsp">首页</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    精选板块 <b class="caret"></b>
                </a>
            </li>
            <li><a href="<%=request.getContextPath() %>/more.action?type=-3&&page=1">论坛热帖</a></li>
            <li><a href="<%=request.getContextPath() %>/more.action?type=-1&&page=1">论坛新帖</a></li>
            <li><a href="<%=request.getContextPath() %>/more.action?type=-2&&page=1">精华帖</a></li>
        </ul>
    </div>
    <form name="fileForm" class="navbar-form navbar-right" role="search" action="<%=request.getContextPath()%>/searchTopic">
        <div class="input-group">
            <input type="text" class="form-control" name="keywords" placeholder="search">
            <span class="input-group-addon"><span onclick="spanSubmit()" class="glyphicon glyphicon-search" ></span></span>
        </div>
    </form>
</nav>
</body>
</html>
