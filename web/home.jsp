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
    <title>Title</title>
<%--    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>--%>
<%--    <script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>--%>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <link href="<%=basePath%>css/zzsc-demo.css" type="text/css" rel="stylesheet">
    <link href="<%=basePath%>css/index.css" type="text/css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap.min.css" type="text/css" rel="stylesheet">
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
                            <img src="<%=basePath%>image/2.jpg" alt="...">
                            <div class="carousel-caption">
                            </div>
                        </div>
                        <div class="item">
                            <img src="<%=basePath%>image/1.jpg" alt="...">
                            <div class="carousel-caption">
                            </div>
                        </div>
                        <div class="item">
                            <img src="<%=basePath%>image/2.jpg" alt="...">
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
                                        value="replyNum"/>&nbsp;发表日期:<s:property value="createTime"/></p>
                            </a>
                        </s:iterator>
                    </ul>
                </div>

            </div>
        </div>
    </div>
</div>

<div style="margin-top: 80px;background-color: rgba(0,0,0,0.8);height: 100px;color: darkgray;width: 100%">
    <div style="width: 400px;padding-top: 35px;padding-left:40px;padding-right: 40px;margin:auto;">
        <div>
            友情链接：
            <a href="https://github.com/tony-coder" style="color: darkgray">&nbsp;github&nbsp;|&nbsp;</a>
            <a href="http://www.csdn.net/" style="color: darkgray">csdn&nbsp;|&nbsp;</a>
            <a href="http://www.oschina.net/" style="color: darkgray">开源中国&nbsp;|&nbsp;</a>
            <a href="http://stackoverflow.com/" style="color: darkgray">stackflow</a><br>
            小组成员：鲍锋雄、程凯、王梦欣
        </div>
    </div>
</div>


</body>
</html>