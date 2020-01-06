<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <base href="<%=basePath%>">
    <title>帖子详情</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link href="css/titlebar.css" rel="stylesheet">
    <link href="css/search-result.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="/page/header.jsp"/>
<!-- 帖子列表 -->
<div class="list-group search-list">
    <a href="#" class="list-group-item active">
        <s:if test="%{#request.topics.size() > 0}">
            <s:property value="%{#request.typename}" />
        </s:if>
        <s:else>
            抱歉！没有更多的结果
        </s:else>
    </a>
    <s:iterator value="%{#request.topics}" var="topic">
    <a href="<%=path%>/page/topic.jsp?topicId=<s:property value="%{#topic.id}"/>&&page=1" class="list-group-item">
        <h4 class="list-group-item-heading">
            <s:property value="#topic.getSubSectionBySectionId().getMainSectionByMainSectionId().getTitle()"/>
        </h4>
        <s:property value="%{#topic.getTitle()}"/>&nbsp;<s:property value="#topic.getSubSectionBySectionId().getTitle()"/>
        <p class="text-right post-date">点击量:<s:property value="%{#topic.getClick()}"/>&nbsp;
            评论量:<s:property value="%{#topic.getReplyNum()}"/>&nbsp;
            发表日期:<s:property value="%{#topic.getCreateTime()}"/></p>
    </a>
    </s:iterator>
</div>
<ul class="pagination pagination-lg page-float">
    <s:if test="%{#request.page > 1}">
        <li><a href="<%=path%>/more.action?type=<s:property value="%{#request.type}"/>&&page=<s:property value="%{#request.page - 1}"/>">&laquo;</a></li>
    </s:if>
    <s:if test="%{#request.page <= 5}">
        <s:iterator begin="1" end="5" var="i"><%--for循环--%>
            <s:if test="%{#request.page == #i}"><%--如果与i 相等--%>
                <li class="active">
                    <a href="<%=path%>/more.action?type=<s:property value="%{#request.type}"/>&&page=<s:property value="%{#i}"/>">
                        <s:property value="%{#i}"/>
                    </a></li>
            </s:if>
            <s:else><%--与i不一样--%>
                <li><a href="<%=path%>/more.action?type=<s:property value="%{#request.type}"/>&&page=<s:property value="%{#i}"/>">
                        <s:property value="%{#i}"/>
                    </a></li>
            </s:else>
            <s:if test="%{#i == 5}"><%--如果 i=5--%>
                <li><a href="<%=path%>/more.action?type=<s:property value="%{#request.type}"/>&&page=6">&raquo;</a></li>
            </s:if>
        </s:iterator>
    </s:if>
    <s:if test="%{#request.page > 5}">
        <s:iterator value="{4,3,2,1,0}" var="i"><%--for循环--%>
            <s:if test="%{#i == 0}"><%--如果i =0相等--%>
                <li class="active">
                    <a href="<%=path%>/more.action?type=<s:property value="%{#request.type}"/>&&page=<s:property value="%{#request.page - #i}"/>">
                        <s:property value="%{#request.page - #i}"/>
                    </a></li>
            </s:if>
            <s:else>
                <li><a href="<%=path%>/more.action?type=<s:property value="%{#request.type}"/>&&page=<s:property value="%{#request.page - #i}"/>">
                        <s:property value="%{#request.page - #i}"/>
                    </a></li>
            </s:else>
        </s:iterator>
        <li><a href="<%=path%>/more.action?type=<s:property value="%{#request.type}"/>&&page=<s:property value="%{#request.page + 1}"/>">&raquo;</a></li>
    </s:if>
</ul><br>
</body>
</html>
