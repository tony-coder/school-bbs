<%@ taglib prefix="s" uri="/struts-tags" %>
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

    <title>屏蔽用户</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

    <%--<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>--%>
    <%--<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>--%>
    <%--<link href="<%=basePath%>css/bootstrap.min.css" type="text/css" rel="stylesheet">--%>
    <script type="text/javascript">
        function spanSubmit1(){
            var keywords = document.fileForm1.keywords;
            if(keywords.value.length==0) {
                alert("请输入搜索信息");
            }else{
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
                <li role="presentation" ><a href="<%=path%>/manager/notice.jsp">发布公告</a></li>
                <li role="presentation"><a href="<%=path%>/manager/change_info.jsp">资料修改</a></li>
                <li role="presentation"><a href="<%=path%>/manager/newTopics.jsp">查看新帖</a></li>
                <li role="presentation"><a href="<%=path%>/manager/bestpost.jsp">精华帖请求</a></li>
                <li role="presentation" class="active"><a href="<%=path%>/manager/limitUser.jsp">封锁用户</a></li>
                <li role="presentation"><a href="<%=path%>/manager/create_discuss.jsp">创建讨论区</a></li>
                <li role="presentation"><a href="<%=path%>/manager/sensitiveWords.jsp">敏感词管理</a></li>
            </ul>
        </div>

        <div class="col-md-9">
            <form name="fileForm1" style="float: right;" class="navbar-form navbar-right" role="search" action="<%=request.getContextPath()%>/searchUser">
                <div class="input-group" style="width: 300px">
                    <input type="text"  class="form-control" name="keywords" placeholder="搜索用户(用户名/邮箱)">
                    <span class="input-group-addon"><span onclick="spanSubmit1()" class="glyphicon glyphicon-search" ></span> </span>
                </div>
            </form>

            <div style="margin-top: 50px;">
                <ul class="list-group">

                    <s:iterator value="%{#request.users}" var="user">
                        <div class="list-group-item" style="height: 60px;padding-top: 20px;">
                            <a href="" style="color:grey">
                                用户名:<s:property value="%{#user.username}"/>&nbsp;邮箱:<s:property value="%{#user.email}" />
                            </a>
                            <div class="btn-group" style="float: right;margin-right: 20px;">
                                <button  type="button" class="btn btn-default dropdown-toggle btn-xs"
                                         data-toggle="dropdown">
                                    封锁用户 <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="<%=path%>/limitUser.action?userId=<s:property value="%{#user.id}"/>&&level=-1&&keywords=<s:property value="%{#request.keywords}"/>">解除限制</a></li>
                                    <li><a href="<%=path%>/limitUser.action?userId=<s:property value="%{#user.id}"/>&&level=4&&keywords=<s:property value="%{#request.keywords}"/>">限制回复</a></li>
                                    <li><a href="<%=path%>/limitUser.action?userId=<s:property value="%{#user.id}"/>&&level=3&&keywords=<s:property value="%{#request.keywords}"/>">限制发帖</a></li>
                                    <li><a href="<%=path%>/limitUser.action?userId=<s:property value="%{#user.id}"/>&&level=2&&keywords=<s:property value="%{#request.keywords}"/>">限制发帖与回复</a></li>
                                    <li><a href="<%=path%>/limitUser.action?userId=<s:property value="%{#user.id}"/>&&level=1&&keywords=<s:property value="%{#request.keywords}"/>">限制登陆</a></li>
                                </ul>
                            </div>
                            <div style="float: right;margin-right: 10px;">
                                限制状态：
                                <s:iterator value="%{#user.blackListsById}" var="blackListsById">
                                    <s:if test="%{#blackListsById.level < 0}">
                                    无限制
                                    </s:if>
                                    <s:elseif test="%{#blackListsById.level == 1 }">
                                    禁止登陆
                                    </s:elseif>
                                    <s:elseif test="%{#blackListsById.level == 2 }">
                                    禁止发帖和回复
                                    </s:elseif>
                                    <s:elseif test="%{#blackListsById.level == 3 }">
                                    禁止发帖
                                    </s:elseif>
                                    <s:elseif test="%{#blackListsById.level == 4 }">
                                    禁止回复
                                    </s:elseif>
                                    <%--<s:property value="%{#blackListsById.level}"/>--%>
                                </s:iterator>

                            </div>
                        </div>
                    </s:iterator>

                </ul>
            </div>
        </div>
        </div>
    </div>
</div>

</body>
</html>
