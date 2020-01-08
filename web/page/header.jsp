<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2020/1/2
  Time: 1:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/titlebar.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link href="css/search-result.css" rel="stylesheet"/>
    <link href="css/post-detail.css" rel="stylesheet">
    <script type="text/javascript">
        function spanSubmit() {
            document.fileForm.submit();
        }
    </script>
</head>

<body>


<nav class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">ZJUT校园论坛</a>
    </div>

    <div>
        <ul class="nav navbar-nav">
            <li><a href="<%=path%>/index.jsp">首页</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    精选板块 <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <s:iterator value="%{#session.mainSections}" var="mainSection">
                        <li class="divider"></li>
                        <li>
                            <a href="more.action?type=<s:property value="%{#mainSection.id}"/> &&page=1"><s:property
                                    value="%{#mainSection.title}"/></a></li>
                    </s:iterator>
                </ul>

            </li>
            <li><a href="<%=request.getContextPath() %>/more.action?type=-3&&page=1">论坛热帖</a></li>
            <li><a href="<%=request.getContextPath() %>/more.action?type=-1&&page=1">论坛新帖</a></li>
            <li><a href="<%=request.getContextPath() %>/more.action?type=-2&&page=1">精华帖</a></li>
        </ul>
    </div>


    <s:if test="%{#session.user==null}">
        <ul class="nav navbar-nav navbar-right user">
            <li><a href="<%=request.getContextPath()%>/login.jsp">登陆</a></li>
            <li><a href="<%=request.getContextPath()%>/register.jsp">注册</a></li>
        </ul>
        <p class="navbar-text navbar-right">尊敬的游客您好！</p>
    </s:if>
    <s:elseif test="%{#session.user.privilege==0}">
        <ul class="nav navbar-nav navbar-right user">

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <s:property value="%{#session.user.username}"/> <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="<%=path+"/page/change-info.jsp"%>">设置</a></li>
                    <li><a href="mytopic.action">我的帖子</a></li>
                    <li><a href="editTopic.action">我要发帖</a></li>
                    <li class="divider"></li>
                    <li><a href="<%=path+"/logout.action"%>">退出登陆</a></li>
                </ul>
            </li>
        </ul>
        <p class="navbar-text navbar-right">尊敬的用户您好！</p>
    </s:elseif>
    <s:elseif test="%{#session.user.privilege==1}">
        <ul class="nav navbar-nav navbar-right user">

            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <s:property value="%{#session.user.username}"/><b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li ><a href="<%=path%>/manager/notice.jsp">发布公告</a></li>
                    <li ><a href="<%=path%>/manager/change_info.jsp">资料修改</a></li>
                    <li ><a href="more.action?type=-1&&page=1&&isAdmin=1">查看新帖</a></li>
                    <li ><a href="bestTopic.action">精华帖请求</a></li>
                    <li ><a href="getAllLimitUsers.action">封锁用户</a></li>
                    <li ><a href="<%=path%>/manager/create_discuss.jsp">创建讨论区</a></li>
                    <li ><a href="getAllSensitiveWords">敏感词管理</a></li>
                    <li class="divider"></li>
                    <li><a href="<%=path+"/logout.action"%>">退出登陆</a></li>
                </ul>
            </li>
        </ul>
        <p class="navbar-text navbar-right">尊敬的管理员您好！</p>

    </s:elseif>

    <form name="fileForm" class="navbar-form navbar-right" role="search" action="<%=request.getContextPath()%>/searchTopic">
        <div class="input-group">
            <input type="text" class="form-control" name="keywords" placeholder="search">
            <span class="input-group-addon"><span onclick="spanSubmit()" class="glyphicon glyphicon-search"></span></span>
        </div>
    </form>
</nav>

</body>
</html>