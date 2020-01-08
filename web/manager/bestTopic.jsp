<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/1/7
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>精华帖申请</title>
</head>
<body>

<jsp:include page="/page/header.jsp"/>

<div class="container" style="margin-top: 80px">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation" ><a href="<%=path%>/manager/notice.jsp">发布公告</a></li>
                <li role="presentation"><a href="<%=path%>/manager/change_info.jsp">资料修改</a></li>
                <li role="presentation" ><a href="more.action?type=-1&&page=1&&isAdmin=1">查看新帖</a></li>
                <li role="presentation" class="active"><a href="bestTopic.action">精华帖请求</a></li>
                <li role="presentation" ><a href="getAllLimitUsers.action">封锁用户</a></li>
                <li role="presentation"><a href="<%=path%>/manager/create_discuss.jsp">创建讨论区</a></li>
                <li role="presentation"><a href="getAllSensitiveWords">敏感词管理</a></li>
            </ul>
        </div>

        <div class="col-md-9">

            <ul class="list-group">
                <a class="list-group-item active">
                    精华帖申请列表
                </a>

                <s:iterator value="#request.bestTopics">
                <s:if test="state==1">
                <div class="list-group-item">
                    <a href="topicDetail.action?topicId=<s:property value="topicByTopicId.id"/> &&page=1"
                       style="color:grey">
                        <h4 class="list-group-item-heading" style="color:black">
                            [<s:property value="topicByTopicId.subSectionBySectionId.mainSectionByMainSectionId.title"/>
                            ]</h4>
                        <s:property value="topicByTopicId.title"/>
                    </a>
                    <a href="replyapply.action?bestTopic.topicByTopicId.id=<s:property value="topicByTopicId.id"/>&&bestTopic.state=3"
                       style="float: right">&nbsp;拒绝</a>&nbsp;
                    <a href="replyapply.action?bestTopic.topicByTopicId.id=<s:property value="topicByTopicId.id"/>&&bestTopic.state=2"
                       style="float: right">&nbsp;同意</a>
                    <p style="float: right;margin-right: 50px">评论量:<s:property value="topicByTopicId.replyNum"/> &nbsp;发表日期:<s:property
                            value="topicByTopicId.updateTime"/>
                    </p>
                </div>
                </s:if>
                </s:iterator>

                <ul class="pagination pagination-lg" style="float:right">
                    <s:if test="#request.pageNum > 1">
                        <s:set name="pageIndex" value="#request.pageNum - 1"/>
                        <li><a href="bestTopic.action?pageNum=<s:property value="#pageIndex"/>"> &laquo;</a></li>
                    </s:if>
                    <s:if test="#request.pageNum <=5">
                        <s:bean name="org.apache.struts2.util.Counter" id="counter">
                            <s:param name="first" value="0"/>
                            <s:param name="last" value="4"/>
                            <s:iterator>
                                <s:if test="#request.pageNum == #counter.current">
                                    <li class="active"><a
                                            href="bestTopic.action?pageNum=<s:property value="#counter.current"/>"><s:property
                                            value="#counter.current"/>
                                    </a></li>
                                </s:if>
                                <s:else>
                                    <li>
                                        <a href="bestTopic.action?pageNum=<s:property value="#counter.current"/>"><s:property
                                                value="#counter.current"/>
                                        </a></li>
                                </s:else>
                                <s:if test="#counter.current == 5">
                                    <li><a href="bestTopic.action?pageNum=6">&raquo;</a></li>
                                </s:if>
                            </s:iterator>
                        </s:bean>
                    </s:if>
                    <s:if test="#request.pageNum >5">
                        <s:bean name="org.apache.struts2.util.Counter" id="counter">
                            <s:param name="first" value="0"/>
                            <s:param name="last" value="4"/>
                            <s:set name="maxPage" value="#request.pageNum + 1"/>
                            <s:iterator>
                                <s:set name="pageIndex" value="#request.pageNum - 5 + #counter.current"/>
                                <s:if test="#counter.current == 5">
                                    <li class="active"><a
                                            href="bestTopic.action?pageNum=<s:property value="#pageIndex"/>"><s:property
                                            value="#pageIndex"/>
                                    </a></li>
                                </s:if>
                                <s:else>
                                    <li class=""><a
                                            href="bestTopic.action?pageNum=<s:property value="#pageIndex"/>"><s:property
                                            value="#pageIndex"/>
                                    </a></li>
                                </s:else>
                            </s:iterator>
                        </s:bean>
                        <li><a href="bestTopic.action?pageNum=<s:property value="#maxPage"/>">&raquo;</a></li>
                    </s:if>
                </ul>
                <br>

        </div>
    </div>
</div>
</body>
</html>