<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/14
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";  //获取web应用根目录路径
%>
<html>
<head>
    <title>论坛主页</title>
    <link href="<%=basePath%>css/index.css" type="text/css" rel="stylesheet">
</head>
<body>

<jsp:include page="/page/header.jsp"/>
<div style="width: 100%;">
    <div class="container user-info">
        <div class="row">
            <div class="col-xs-12">
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <img src="<%=basePath%>img/2.jpg" alt="...">
                            <div class="carousel-caption">
                            </div>
                        </div>
                        <div class="item">
                            <img src="<%=basePath%>img/1.jpg" alt="...">
                            <div class="carousel-caption">
                            </div>
                        </div>
                        <div class="item">
                            <img src="<%=basePath%>img/2.jpg" alt="...">
                            <div class="carousel-caption">
                            </div>
                        </div>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>

        <div class="container" style="margin-top: 30px;">
            <div class="row">
                <div class="col-md-9">
                    <ul class="list-group">
                        <div class="list-group-item active">
                            论坛新帖
                            <a href="<%=request.getContextPath() %>/more.action?type=-1&&page=1"
                               style="float: right;color: white">更多>></a>
                            <!--<p style="float: right"></p>-->
                        </div>
                        <s:iterator value="#request.topics">
                            <a href="<%=path%>/topicDetail.action?topicId=<s:property value="id"/>&&page=1"
                               class="list-group-item">
                                <h4 class="list-group-item-heading" style="color:black">[<s:property
                                        value="subSectionBySectionId.mainSectionByMainSectionId.title"/>]</h4>
                                <s:property value="title"/><span class="badge">新</span>
                                <p class="text-right" style="float: right;margin-right: 20px">浏览量:<s:property
                                        value="click"/>&nbsp;评论量:<s:property
                                        value="replyNum"/>&nbsp;发表日期:<s:date name="createTime"
                                                                             format="yyyy-MM-dd HH:mm:ss"/></p>
                            </a>
                        </s:iterator>
                    </ul>
                </div>
                <div class="col-md-3">
                    <ul class="list-group">
                        <div class="list-group-item active">
                            论坛公告
                        </div>

                        <s:iterator value="#request.notices">
                            <a href="notice.action?notice.id=<s:property value="id"/> "
                               class="list-group-item"><s:property value="title"/>
                            </a>
                        </s:iterator>
                    </ul>
                    <a href="<%=basePath%>publish_post.jsp">
                        <button type="button" class="btn btn-primary"
                                style="width: 200px;height:50px;margin-left: 30px">我要发帖
                        </button>
                    </a>
                </div>
            </div>

            <div class="row">
                <div class="col-md-9">
                    <ul class="list-group">
                        <div class="list-group-item active">
                            精华帖
                            <a href="<%=request.getContextPath() %>/more.action?type=-2&&page=1"
                               style="float: right;color: white">更多>></a>
                        </div>
                        <s:iterator value="#request.bestTopics">
                            <a href="topicDetail.action?topicId=<s:property value="topicByTopicId.id"/>&&page=1"
                               class="list-group-item">
                                <h4 class="list-group-item-heading">
                                    [<s:property
                                        value="topicByTopicId.subSectionBySectionId.mainSectionByMainSectionId.title"/>]</h4>
                                <s:property value="topicByTopicId.title"/><span class="badge">热</span>
                                <p class="text-right" style="float: right;margin-right: 20px">
                                    浏览量<s:property value="topicByTopicId.click"/>&nbsp;评论量:<s:property
                                        value="topicByTopicId.replyNum"/>&nbsp;发表日期:<s:date name="createTime"
                                                                                            format="yyyy-MM-dd HH:mm:ss"/>
                                </p>
                            </a>
                        </s:iterator>
                    </ul>
                </div>
            </div>

            <h3 style="margin-top: 20px;margin-left: 15px">板块分类</h3>
            <hr/>
            <div class="container">
                <s:set name="i" value="1"/>
                <s:iterator value="#session.mainSections" var="top">
                    <s:if test="#i % 4 == 1">
                        <div class="row">
                    </s:if>
                    <div class="col-md-3 col-sm-12">
                        <a href="<%=request.getContextPath() %>/more.action?type=<s:property value="id"/>&&page=1">
                            <div class="main-forum">
                                <h3><s:property value="#top.title"/></h3>
                                <s:set name="switcher" value="true"/>
                                <s:iterator value="#top.subSectionsById" var="inner">
                                    <s:if test="#switcher == true">
                                        <s:set name="switcher" value="false"/><s:property value="#inner.title"/>
                                    </s:if>
                                    <s:else>
                                        <s:set name="switcher" value="true"/><s:property value="#inner.title"/>
                                    </s:else>

                                </s:iterator>
                            </div>
                        </a>
                    </div>
                    <s:if test="#i % 4 ==4">
                        </div>
                    </s:if>
                    <s:set name="i" value="#i + 1"/>
                </s:iterator>
            </div>
        </div>
    </div>
</div>

<jsp:include page="page/bottom.jsp"/>

</body>
</html>