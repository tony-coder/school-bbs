<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/23
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";  //获取web应用根目录路径
%>
<html>
<head>
    <title>帖子浏览</title>
    <link href="css/post-detail.css" rel="stylesheet">
    <%-- <script type="text/javascript" src="ckeditor/ckeditor.js"></script>--%>
</head>
<body>
<div style="height:100%">

    <%-- <jsp:include page="/pages/header.jsp"/>--%>
    <div class="container" style="margin-top: 50px">
        <div class="row">
            <div class="col-md-1 post-border"></div>
            <div class="col-md-2 post-head">
                <!--<img src="img/tm-bg-slide-1.jpg" width="80px" height="80px" class="img-responsive img-circle">-->
                <img alt="" class="img-responsive img-circle"
                     src="<%=basePath%> + <s:property value="#session.topic.getUserByUserId().getUserAvatarUrl()"/>"
                     style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>
                <span class="user-info">
                            <span class="badge" style="background: #f1c40f;margin-top: 5px">发帖者</span>
                            :<span class="badge"
                                   style="background: #f1c40f;margin-top: 5px"><s:property
                        value="#session.topic.getUserByUserId().getUsername()"/> </span>
                        </span><br/>
                <span class="user-info">
                            <span class="badge" style="background: #2ecc71;margin-top: 5px">性别</span>
                            :<span class="badge"
                                   style="background: #2ecc71;margin-top: 5px"><s:property
                        value="#session.topic.getUserByUserId().getSex()"/></span>
                        </span><br/>
                <span class="user-info">
                             <span class="badge" style="background: #ff6927;margin-top: 5px">论坛等级</span>:
                            <span class="badge" style="background: #ff6927;margin-top: 5px">LV<s:property
                                    value="#session.topic.getUserByUserId().getLevel()"/></span>
                </span><br>
            </div>
            <div class="col-md-8 post-content">

            </div>
            <div class="col-md-1 post-border">

            </div>
        </div>
    </div>

</body>
</html>
