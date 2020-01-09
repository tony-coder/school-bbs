<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <title>资料修改</title>
    <base href="<%=basePath%>">
    <%--<link href="css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="css/search-result.css" rel="stylesheet"/>--%>
</head>
<body>
<jsp:include page="/page/header.jsp"/>
<div class="container" style="margin-top: 30px">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation" class="active"><a href="<%=path%>/page/change-info.jsp">资料修改</a></li>
                <li role="presentation"><a href="mytopic.action">我的帖子</a></li>
                <li role="presentation"><a href="applyrecords.action">申请记录</a></li>
            </ul>
        </div>

        <div class="col-xs-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        个人信息修改
                    </h3>
                </div>
                <div class="panel-body">
                    <form id="form1" action="updateUser.action" method="post" enctype="multipart/form-data">
                        <div class="column">
                            <div class="form-group">
                                <img class="avatar left" height="70"
                                     src="<%=path%><s:property value="#session.user.getUserAvatarUrl()"/>" width="70"/>
                                请上传你的头像<br/>
                            </div>
                            <input type="file" id="inputfile" accept="image/*" name="photoImg"><br/>

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
                                <dt><%--@declare id="user_profile_company"--%><label
                                        for="user_profile_company">密码</label></dt>
                                <dd><input id="password" class="form-control" name="user.password" size="30"
                                           type="password" width="200px"/></dd>
                            </dl>
                            <dl class="form-group">
                                <dt><%--@declare id="user_profile_location"--%><label
                                        for="user_profile_location">重复密码</label></dt>
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
