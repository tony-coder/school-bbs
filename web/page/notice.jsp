<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/1/8
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";  //获取web应用根目录路径
%>
<html>
<head>
    <title>notice</title>
    <link href="<%=basePath%>css/post-detail.css" rel="stylesheet">
</head>
<body>
<div style="height:100%">
    <jsp:include page="/page/header.jsp"/>
    <div class="container" style="margin-top: 50px">
        <div class="row">
            <div class="col-md-1 post-border"></div>
            <div class="col-md-2 post-head">
                <!--<img src="img/tm-bg-slide-1.jpg" width="80px" height="80px" class="img-responsive img-circle">-->
                <img alt="" class="img-responsive img-circle"
                     src="<%=path%><s:property value="#request.notice.userByUserId.userAvatarUrl"/>"
                     style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>

                <span class="user-info"><span class="badge"
                                              style="background: #f1c40f;margin-top: 5px">公告发布者</span>:<span
                        class="badge" style="background: #f1c40f;margin-top: 5px">
                    <s:property value="#request.notice.userByUserId.username"/></span></span><br/>
                <span class="user-info"><span class="badge" style="background: #2ecc71;margin-top: 5px">性别</span>:<span
                        class="badge" style="background: #2ecc71;margin-top: 5px">
                    <s:property value="#request.notice.userByUserId.sex"/></span></span><br/>
                <br>
            </div>

            <div class="col-md-8 post-content">
                <div class="post-title">
                    <h2 style="margin-left:20px;color:black"><s:property value="#request.notice.title"/></h2>
                    <strong style=" float:right;margin-right:10px;margin-top: 10px"><span class="badge" style="background: #ff6927;width: 50px;">管理员</span></strong>
                </div>
                <div style="margin: 20px">
                    <s:property value="#request.notice.content"/>
                </div>
            </div>
            <div class="col-md-1 post-border">

            </div>
        </div>
    </div>
</div>
</body>
</html>