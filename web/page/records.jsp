<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/1/8
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="/page/header.jsp"/>

<div class="container" style="margin-top: 30px">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a href="<%=path%>/page/change-info.jsp">资料修改</a></li>
                <li role="presentation"><a href="mytopic.action">我的帖子</a></li>
                <li role="presentation" class="active"><a href="applyrecords.action">申请记录</a></li>
                <!--<li role="presentation"><a href="#">Messages</a></li>-->
            </ul>
        </div>

        <div class="col-md-9">

            <ul class="list-group">
                <a class="list-group-item active">
                    申请记录
                </a>

                <s:set name="stateTmp" value="'未知'"/>
                <s:iterator value="#request.bestTopics">
                    <%--<s:property value="state"/>--%>
                    <s:if test="state == 1">
                        <s:set name="stateTmp" value="'等待审核中'"/>
                    </s:if>
                    <s:elseif test="state == 2">
                        <s:set name="stateTmp" value="'已同意'"/>
                    </s:elseif>
                    <s:elseif test="state == 3">
                        <s:set name="stateTmp" value="'已被拒绝'"/>
                    </s:elseif>
                    <div class="list-group-item">
                        <a href="topicDetail.action?topicId=<s:property value="topicByTopicId.id"/>&&page=1"
                           style="color:grey">
                            <h4 class="list-group-item-heading" style="color:black">
                                [<s:property
                                    value="topicByTopicId.subSectionBySectionId.mainSectionByMainSectionId.title"/>
                                ]</h4>
                            <s:property value="topicByTopicId.title"/>
                        </a>
                        <p style="float: right;margin-right: 50px">状态：<s:property value="#stateTmp"/></p>
                    </div>
                </s:iterator>
            </ul>

            <ul class="pagination pagination-lg" style="float:right">
                <s:if test="#request.pageNum > 1">
                    <s:set name="pageIndex" value="#request.pageNum - 1"/>
                    <li><a href="applyrecords.action?pageNum=<s:property value="#pageIndex"/>"> &laquo;</a></li>
                </s:if>
                <s:if test="#request.pageNum <=5">
                    <s:bean name="org.apache.struts2.util.Counter" id="counter">
                        <s:param name="first" value="0"/>
                        <s:param name="last" value="4"/>
                        <s:iterator>
                            <s:if test="#request.pageNum == #counter.current">
                                <li class="active"><a
                                        href="applyrecords.action?pageNum=<s:property value="#counter.current"/>"><s:property
                                        value="#counter.current"/>
                                </a></li>
                            </s:if>
                            <s:else>
                                <li>
                                    <a href="applyrecords.action?pageNum=<s:property value="#counter.current"/>"><s:property
                                            value="#counter.current"/>
                                    </a></li>
                            </s:else>
                            <s:if test="#counter.current == 5">
                                <li><a href="applyrecords.action?pageNum=6">&raquo;</a></li>
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
                                        href="applyrecords.action?pageNum=<s:property value="#pageIndex"/>"><s:property
                                        value="#pageIndex"/>
                                </a></li>
                            </s:if>
                            <s:else>
                                <li class=""><a
                                        href="applyrecords.action?pageNum=<s:property value="#pageIndex"/>"><s:property
                                        value="#pageIndex"/>
                                </a></li>
                            </s:else>
                        </s:iterator>
                    </s:bean>
                    <li><a href="applyrecords.action?pageNum=<s:property value="#maxPage"/>">&raquo;</a></li>
                </s:if>
            </ul>
            <br>

        </div>
    </div>
</div>


</body>
</html>
