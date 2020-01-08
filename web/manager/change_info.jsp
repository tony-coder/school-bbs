<%@page import="cn.edu.zjut.po.User" %>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="cn.edu.zjut.serviceImpl.AdminServiceImpl" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>管理员修改个人信息</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
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
                <li role="presentation" class="active"><a href="<%=path%>/manager/change_info.jsp">资料修改</a></li>
                <li role="presentation"><a href="more.action?type=-1&&page=1&&isAdmin=1">查看新帖</a></li>
                <li role="presentation"><a href="bestTopic.action">精华帖请求</a></li>
                <li role="presentation" ><a href="getAllLimitUsers.action">封锁用户</a></li>
                <li role="presentation"><a href="<%=path%>/manager/create_discuss.jsp">创建讨论区</a></li>
                <li role="presentation"><a href="getAllSensitiveWords">敏感词管理</a></li>
            </ul>
        </div>

        <div class="col-md-9">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        个人信息修改
                    </h3>
                </div>
                <div class="panel-body">
                    <form id="form1" action="updateAdmin" method="post" enctype="multipart/form-data">

                        <div class="column">

                            <div class="form-group">
                                <img class="avatar left" height="70"
                                     src="<%=path%><s:property value="#session.user.getUserAvatarUrl()"/>" width="70"/>
                                请上传你的头像<br/>
                            </div>
                            <input type="file" id="inputfile" accept="image/*" name="photoImg">文件大小不得超过1M<br/>

                            <br/>

                            <div class="form-group">
                                <label for="name">用户名</label>
                                <input id="username" type="text" class="form-control" id="name" width="200px"
                                       height="80px" placeholder="请输入名称" name="user.username"
                                       value="<s:property value="#session.user.getUsername()"/>"><s:fielderror
                                    fieldName="username"/>
                            </div>

                            <div class="form-group">
                                <label for="name">性 别</label><br/>
                                <s:if test='%{#session.user.sex=="男"}'>
                                    <s:radio name="user.sex" list="%{#{'1':'男','0':'女'}}" value="1"/>
                                </s:if>
                                <s:else>
                                    <s:radio name="user.sex" list="%{#{'1':'男','0':'女'}}"/>
                                </s:else>
                            </div>


                            <dl class="form-group">
                                <dt><label for="user_profile_blog">邮箱</label></dt>
                                <dd><input id="email" type="email" class="form-control" id="user_profile_blog"
                                           name="user.email" size="30"
                                           value="<s:property value="#session.user.getEmail()"/>"/></dd>
                                <s:fielderror fieldName="email"/>
                            </dl>
                            <dl class="form-group">
                                <dt><label for="user_profile_company">密码</label></dt>
                                <dd><input id="password" class="form-control" name="user.password" size="30"
                                           type="password" width="200px"/></dd>
                            </dl>
                            <dl class="form-group">
                                <dt><label for="user_profile_location">重复密码</label></dt>
                                <dd><input id="confirm_password" class="form-control" name="confirm_password" size="30"
                                           type="password" width="200px"/></dd>
                            </dl>
                            <p><input type="submit" value="确认修改"></p>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#form1").validate({
            rules: {
                "user.username": {
                    required: true
                },
                "user.email": {
                    required: true,
                    email: true
                },
                "user.password": {
                    rangelength: [6, 20]
                },
                "user.confirm_password": {
                    equalTo: "#password"
                }
            },
            messages: {
                "user.username": {
                    required: "必填"
                },
                "user.email": {
                    required: "必填",
                    email: "E-Mail格式不正确"
                },
                "user.password": {
                    rangelength: $.validator.format("密码最小长度:{0}, 最大长度:{1}。")
                },
                "user.confirm_password": {
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
