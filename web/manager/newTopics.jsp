<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2020/1/7
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>查看最新贴</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <%--<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">--%>
    <%--<link href="css/titlebar.css" rel="stylesheet">--%>
    <%--<link href="css/search-result.css" rel="stylesheet"/>--%>
</head>

<body>

<jsp:include page="/page/header.jsp"/>

<div class="container" style="margin-top: 80px">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a href="<%=path%>/manager/notice.jsp">发布公告</a></li>
                <li role="presentation"><a href="<%=path%>/manager/change_info.jsp">资料修改</a></li>
                <li role="presentation" class="active"><a href="more.action?type=-1&&page=1&&isAdmin=1">查看新帖</a></li>
                <li role="presentation"><a href="bestTopic.action">精华帖请求</a></li>
                <li role="presentation"><a href="getAllLimitUsers.action">封锁用户</a></li>
                <li role="presentation"><a href="<%=path%>/manager/create_discuss.jsp">创建讨论区</a></li>
                <li role="presentation"><a href="getAllSensitiveWords.action">敏感词管理</a></li>
            </ul>
        </div>

        <div class="col-md-9">

            <ul class="list-group">
                <a class="list-group-item active">
                    论坛新帖
                </a>


                <s:iterator value="%{#request.topics}" var="topic">
                    <div class="list-group-item">
                        <a href="topicDetail.action?topicId=<s:property value="%{#topic.id}"/>&&page=1"
                           style="color:grey">
                            <h4 class="list-group-item-heading" style="color:black">[<s:property
                                    value="%{#topic.subSectionBySectionId.mainSectionByMainSectionId.title}"/>]</h4>
                            <s:property value="%{#topic.title}"/>
                        </a>
                        <a href="deleteTopic.action?topicId=<s:property value="%{#topic.id}"/>"
                           style="float: right">删除</a>
                        <p style="float: right;margin-right: 50px">浏览量:<s:property
                                value="%{#topic.click}"/>&nbsp;评论量:<s:property
                                value="%{#topic.replyNum}"/>&nbsp;发表日期:<s:property value="%{#topic.createTime}"/></p>


                    </div>
                </s:iterator>

                <ul class="pagination pagination-lg" style="float:right">
                    <s:if test="%{#request.page > 1}">
                        <li>
                            <a href="<%=path%>/more.action?page=<s:property value="%{#request.page - 1}"/>">&laquo;</a>
                        </li>
                    </s:if>
                    <s:if test="%{#request.page <= 5}">
                        <s:iterator begin="1" end="5" var="i"><%--for循环--%>
                            <s:if test="%{#request.page == #i}"><%--如果与i 相等--%>
                                <li class="active">
                                    <a href="<%=path%>/more.action?page=<s:property value="%{#i}"/>">
                                        <s:property value="%{#i}"/>
                                    </a></li>
                            </s:if>
                            <s:else><%--与i不一样--%>
                                <li><a href="<%=path%>/more.action?page=<s:property value="%{#i}"/>">
                                    <s:property value="%{#i}"/>
                                </a></li>
                            </s:else>
                            <s:if test="%{#i == 5}"><%--如果 i=5--%>
                                <li><a href="<%=path%>/more.action?page=6">&raquo;</a></li>
                            </s:if>
                        </s:iterator>
                    </s:if>
                    <s:if test="%{#request.page > 5}">
                        <s:iterator value="{4,3,2,1,0}" var="i"><%--for循环--%>
                            <s:if test="%{#i == 0}"><%--如果i =0相等--%>
                                <li class="active">
                                    <a href="<%=path%>/more.action?page=<s:property value="%{#request.page - #i}"/>">
                                        <s:property value="%{#request.page - #i}"/>
                                    </a></li>
                            </s:if>
                            <s:else>
                                <li>
                                    <a href="<%=path%>/more.action?page=<s:property value="%{#request.page - #i}"/>">
                                        <s:property value="%{#request.page - #i}"/>
                                    </a></li>
                            </s:else>
                        </s:iterator>
                        <li>
                            <a href="<%=path%>/more.action?page=<s:property value="%{#request.page + 1}"/>">&raquo;</a>
                        </li>
                    </s:if>
                </ul>
                <br>
            </ul>
        </div>

    </div>
</div>

</body>
</html>
