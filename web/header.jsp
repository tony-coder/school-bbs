<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/1/1
  Time: 20:20
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
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <link href="<%=basePath%>css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
        function spanSubmit(){
            var keywords = document.fileForm.keywords;
            if(keywords.value.length==0) {
                alert("请输入搜索信息");
            }else{
                document.fileForm.submit();
            }
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

                <ul class="dropdown-menu">
                    <s:iterator value="#request.main">
                        <li class="divider"></li>
                        <li>
                            <a href="<%=request.getContextPath() %>/more.action?type=<s:property value="id"/>&&page=1"><s:property
                                    value="title"/></a></li>
                    </s:iterator>

                </ul>

            </li>
            <li><a href="<%=request.getContextPath() %>/more.action?type=-3&&page=1">论坛热帖</a></li>
            <li><a href="<%=request.getContextPath() %>/more.action?type=-1&&page=1">论坛新帖</a></li>
            <li><a href="<%=request.getContextPath() %>/more.action?type=-2&&page=1">精华帖</a></li>
        </ul>
    </div>


    <% String username = (String) session.getAttribute("username");
        String adminname = (String) session.getAttribute("adminname");
        if (username == null && adminname == null) {
    %>
    <ul class="nav navbar-nav navbar-right user">
        <li><a href="<%=request.getContextPath()%>/login.jsp">登陆</a></li>
        <li><a href="<%=request.getContextPath()%>/regist.jsp">注册</a></li>
    </ul>
    <p class="navbar-text navbar-right">尊敬的游客您好！</p>


    <%
    } else if (username != null && adminname == null) {
    %>
    <ul class="nav navbar-nav navbar-right user">

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <%=username%> <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="<%=path+"/pages/change-info.jsp"%>">设置</a></li>
                <li><a href="<%=path+"/pages/mypost.jsp?page=1"%>">我的帖子</a></li>
                <li><a href="<%=path+"/publish_post.jsp"%>">我要发帖</a></li>
                <li class="divider"></li>
                <li><a href="<%=path+"/logout.action"%>">退出登陆</a></li>
            </ul>
        </li>
    </ul>
    <p class="navbar-text navbar-right">尊敬的用户您好！</p>

    <%
    } else {
    %>

    <ul class="nav navbar-nav navbar-right user">

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <%=adminname%> <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="<%=path%>/manage/notice.jsp">发布公告</a></li>
                <li><a href="<%=path%>/manage/change-admin.jsp">资料修改</a></li>
                <li><a href="<%=path%>/manage/newpost.jsp">查看新帖</a></li>
                <li><a href="<%=path%>/manage/bestpost.jsp">精华帖请求</a></li>
                <li><a href="<%=path%>/manage/limit.jsp">封锁用户</a></li>
                <li><a href="<%=path%>/manage/create_discuss.jsp">创建讨论区</a></li>
                <li class="divider"></li>
                <li><a href="<%=path+"/logout.action"%>">退出登陆</a></li>
            </ul>
        </li>
    </ul>
    <p class="navbar-text navbar-right">尊敬的管理员您好！</p>

    <%} %>
    <form name="fileForm" class="navbar-form navbar-right" role="search" action="<%=request.getContextPath()%>/searchUser.action">
        <div class="input-group">
            <input type="text" class="form-control" name="keywords" placeholder="search">
            <span class="input-group-addon"><span onclick="spanSubmit()" class="glyphicon glyphicon-search"></span> </span>
        </div>
    </form>
</nav>

</body>
</html>
