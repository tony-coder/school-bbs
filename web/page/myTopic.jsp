<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/31
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";  //获取web应用根目录路径
%>
<html>
<head>
    <title>Title</title>
    <%--<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">--%>
</head>
<body>
<jsp:include page="/page/header.jsp"/>
<div class="container" style="margin-top: 30px">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a href="<%=path%>/pages/change-info.jsp">资料修改</a></li>
                <li role="presentation" class="active"><a href="<%=path%>/page/myTopic.jsp">我的帖子</a></li>
                <li role="presentation"><a href="<%=path%>/pages/records.jsp">申请记录</a></li>
                <!--<li role="presentation"><a href="#">Messages</a></li>-->
            </ul>
        </div>
        <div class="col-md-9">
            <ul class="list-group">
                <a class="list-group-item active">
                    我的帖子
                </a>
                <s:iterator value="#request.topics">
                    <div class="list-group-item">
                        <a href="topicDetail.action?topicId=<s:property value="id"/>&&page=1"
                           style="color:grey">
                            <h4 class="list-group-item-heading" style="color:black">[<s:property
                                    value="subSectionBySectionId.mainSectionByMainSectionId.title"/>]</h4>
                            <s:property value="title"/>
                        </a>
                            <%-- <a href="<%=basePath%>/deleteTopic.action?topicId=<s:property value="id"/>"
                                style="float: right">删除</a>--%>
                        <a href="editTopic.action?topic.id=<s:property value="id"/>"
                           style="float: right">编辑</a>
                        <s:if test="type==0">
                            <a href="<%=path%>applybest.action?postId=<s:property value="id"/>" style="float: right">申请精华贴&nbsp;</a>
                        </s:if>

                        <p style="float: right;margin-right: 50px">浏览量:<s:property value="click"/>&nbsp;评论量:<s:property
                                value="replyNum"/>&nbsp;发表日期:<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></p>
                    </div>
                </s:iterator>
            </ul>
            <ul class="pagination pagination-lg" style="float:right">
                <s:if test="#request.pageNum > 1">
                    <s:set name="pageIndex" value="#request.pageNum - 1"/>
                    <li><a href="mytopic.action?page=<s:property value="#pageIndex"/>"> &laquo;</a></li>
                </s:if>
                <s:if test="#request.pageNum <=5">
                    <s:bean name="org.apache.struts2.util.Counter" id="counter">
                        <s:param name="first" value="0"/>
                        <s:param name="last" value="4"/>
                        <s:iterator>
                            <s:if test="#request.pageNum == #counter.current">
                                <li class="active"><a
                                        href="mytopic.action?page=<s:property value="#counter.current"/>"><s:property
                                        value="#counter.current"/>
                                </a></li>
                            </s:if>
                            <s:else>
                                <li>
                                    <a href="mytopic.action?page=<s:property value="#counter.current"/>"><s:property
                                            value="#counter.current"/>
                                    </a></li>
                            </s:else>
                            <s:if test="#counter.current == 5">
                                <li><a href="mytopic.action?page=6">&raquo;</a></li>
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
                                        href="mytopic.action?page=<s:property value="#pageIndex"/>"><s:property
                                        value="#pageIndex"/>
                                </a></li>
                            </s:if>
                            <s:else>
                                <li class=""><a
                                        href="mytopic.action?page=<s:property value="#pageIndex"/>"><s:property
                                        value="#pageIndex"/>
                                </a></li>
                            </s:else>
                        </s:iterator>
                    </s:bean>
                    <li><a href="mytopic.action?page=<s:property value="#maxPage"/>">&raquo;</a></li>
                </s:if>
            </ul>
            <br>
        </div>
    </div>
</div>

</body>
</html>
