<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
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
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%--<jsp:include page="<%=basePath%>header.jsp"/>--%>
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
                        <a href="<%=path%>/topicDetail.action?topicId=<s:property value="id"/>&&page=1" style="color:grey">
                            <h4 class="list-group-item-heading" style="color:black">[<s:property
                                    value="subSectionBySectionId.mainSectionByMainSectionId.title"/>]</h4>
                            <s:property value="title"/>
                        </a>
                       <%-- <a href="<%=basePath%>/deleteTopic.action?topicId=<s:property value="id"/>"
                           style="float: right">删除</a>--%>

                        <a href="<%=path%>/editTopic.action?topic.id=<s:property value="id"/>"
                           style="float: right">编辑</a>
                        <s:if test="type==0">
                            <a href="<%=path%>/applybest.action?postId=<s:property value="id"/>" style="float: right">申请精华贴&nbsp;</a>
                        </s:if>

                        <p style="float: right;margin-right: 50px">浏览量:<s:property value="click"/>&nbsp;评论量:<s:property
                                value="replyNum"/>&nbsp;发表日期:<s:property value="createTime"/></p>
                    </div>

                </s:iterator>
            </ul>

            <%--            <ul class="pagination pagination-lg" style="float:right">
                            <% if (pageNum>1) { int pageIndex = pageNum -1;%>
                            <li><a href="<%=path+"/pages/myTopic.jsp?page="+pageIndex%>">&laquo;</a></li>
                            <%}
                                if (pageNum<=5){
                                    for (int i=1; i<=5; i++){
                                        if (pageNum == i){
                            %>
                            <li class="active"><a href="<%=path+"/myTopic.jsp?page="+i%>"><%=i%></a></li>
                            <%}else {
                            %>
                            <li><a href="<%=path+"/pages/myTopic.jsp?page="+i%>"><%=i%></a></li>
                            <%}
                                if (i ==5){
                            %>
                            <li><a href="<%=path+"/pages/myTopic.jsp?page="+6%>">&raquo;</a></li>
                            <%}}}
                                if (pageNum >5){
                                    int maxPage = pageNum+1;
                                    for (int i=4; i>=0; i--){
                                        int pageIndex = pageNum - i;
                                        if (i==0){
                            %>
                            <li class="active"><a href="<%=path+"/pages/myTopic.jsp?page="+pageIndex%>"><%=pageIndex%></a></li>

                            <%}else {%>
                            <li class=""><a href="<%=path+"/pages/myTopic.jsp?page="+pageIndex%>"><%=pageIndex%></a></li>
                            <%}}%>
                            <li><a href="<%=path+"/pages/myTopic.jsp?page="+maxPage%>">&raquo;</a></li>
                            <%}%>
                        </ul>--%>
            <br>
        </div>
    </div>
</div>

</body>
</html>
