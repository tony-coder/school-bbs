<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: wmxin
  Date: 2020/1/5
  Time: 4:18 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <base href="<%=basePath%>">
    <title>创建讨论区</title>
    <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
    <%--<link href="css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="css/titlebar.css" rel="stylesheet">--%>
    <%--<script src="js/jquery.min.js"></script>--%>
    <%--<script src="js/bootstrap.min.js"></script>--%>
</head>
<body>
<jsp:include page="/page/header.jsp"/>
<div class="container" style="margin-top: 80px">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation" ><a href="<%=path%>/manager/notice.jsp">发布公告</a></li>
                <li role="presentation"><a href="<%=path%>/manager/change_info.jsp">资料修改</a></li>
                <li role="presentation"><a href="more.action?type=-1&&page=1&&isAdmin=1">查看新帖</a></li>
                <li role="presentation"><a href="bestTopic.action">精华帖请求</a></li>
                <li role="presentation"><a href="getAllLimitUsers.action">封锁用户</a></li>
                <li role="presentation"  class="active"><a href="<%=path%>/manager/create_discuss.jsp">创建讨论区</a></li>
                <li role="presentation"><a href="getAllSensitiveWords">敏感词管理</a></li>
            </ul>
        </div>
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        创建讨论区
                    </h3>
                </div>
                <div class="panel-body">
                    <div class="column">
                        <form action="<%=path%>/createDiscuss.action" method="post">
                            <div class="form-group">
                                <label for="name1">大板块名称</label>
                                <input type="text" class="form-control" id="name1" name="mainSection" width="200px" height="80px"
                                       placeholder="请输入大板块名称">
                            </div>
                            <div class="form-group">
                                <label for="name2">子板块名称</label>
                                <input type="text" class="form-control" id="name2" name="subSection" width="200px" height="80px"
                                       placeholder="请输入子板块名称">(多个子版块之间用逗号隔开)
                            </div>
                            <p><button type="submit" class="btn btn-primary">创建板块</button></p>
                    <s:fielderror fieldName="create_result"></s:fielderror>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</html>
