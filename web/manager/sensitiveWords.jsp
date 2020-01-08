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
    <script type="text/javascript">
        function spanSubmit1() {
            var sensitiveWord = document.fileForm1.sensitiveWord;
            if (sensitiveWord.value.length == 0) {
                alert("请输入敏感词");
            } else {
                document.fileForm1.submit();
            }
        }
    </script>
</head>

<body>

<jsp:include page="/page/header.jsp"/>

<div class="container" style="margin-top: 80px">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a href="<%=path%>/manager/notice.jsp">发布公告</a></li>
                <li role="presentation"><a href="<%=path%>/manager/change_info.jsp">资料修改</a></li>
                <li role="presentation"><a href="<%=path%>/manager/newTopics.jsp">查看新帖</a></li>
                <li role="presentation"><a href="bestTopic.action">精华帖请求</a></li>
                <li role="presentation"><a href="<%=path%>/manager/limitUser.jsp">封锁用户</a></li>
                <li role="presentation"><a href="<%=path%>/manager/create_discuss.jsp">创建讨论区</a></li>
                <li role="presentation" class="active"><a href="<%=path%>/manager/sensitiveWords.jsp">敏感词管理</a></li>
            </ul>
        </div>

        <div class="col-md-9">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        敏感词管理
                    </h3>
                </div>
                <div class="panel-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>敏感词</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <s:iterator value="allSensitiveWord" var="sensitiveWord">
                                <td><s:property value="%{#sensitiveWord.id}"/></td>
                                <td><span><s:property value="%{#sensitiveWord.word}"/></span></td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default dropdown-toggle btn-xs"
                                                data-toggle="dropdown">
                                            操作<span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li>
                                                <a href="<%=path%>/deleteSensitiveWord.action?id=<s:property value="%{#sensitiveWord.id}"/>">删除敏感词</a>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                            </s:iterator>
                        </tr>
                        </tbody>
                    </table>
                    <form name="fileForm1" style="float: right;" class="navbar-form navbar-left" role="search"
                          action="<%=request.getContextPath()%>/addSensitiveWord.action">
                        <div class="input-group" style="width: 300px">
                            <input type="text" class="form-control" id="sensitiveWord" name="sensitiveWord.word"
                                   placeholder="请输入敏感词">
                            <span class="input-group-addon"><span onclick="spanSubmit1()">添加</span> </span>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
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
