<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'notice.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <%--<link href="css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="css/titlebar.css" rel="stylesheet">--%>
    <%--<script src="js/jquery.min.js"></script>--%>
    <%--<script src="js/bootstrap.min.js"></script>--%>

</head>

<body>

<jsp:include page="/page/header.jsp"/>

<div class="container" style="margin-top: 80px">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation" class="active"><a href="<%=path%>/manager/notice.jsp">发布公告</a></li>
                <li role="presentation"><a href="<%=path%>/manager/change_info.jsp">资料修改</a></li>
                <li role="presentation"><a href="<%=path%>/manager/newTopics.jsp">查看新帖</a></li>
                <li role="presentation"><a href="bestTopic.action">精华帖请求</a></li>
                <li role="presentation"><a href="<%=path%>/manager/limitUser.jsp">封锁用户</a></li>
                <li role="presentation"><a href="<%=path%>/manager/create_discuss.jsp">创建讨论区</a></li>
                <li role="presentation"><a href="<%=path%>/manager/sensitiveWords.jsp">敏感词管理</a></li>
            </ul>
        </div>

        <div class="col-md-9">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        发布公告
                    </h3>
                </div>
                <div class="panel-body">


                    <div class="column">

                        <form action="<%=path%>/notice.action" method="post">
                            <input type="hidden" name="topic.content" id="content">
                            <div class="form-group">
                                <label for="name">公告标题</label>
                                <input required type="text" class="form-control" name="title" id="name" width="200px"
                                       height="80px"
                                       placeholder="请输入标题">
                            </div>


                            <dl class="form-group">
                                <dt><label>公告内容</label></dt>
                                <dd>
                                    <script id="ueditor" name="topic.content" type="text/plain"
                                            style="height: 300px"></script>
                                </dd>
                            </dl>


                            <p>
                                <button type="submit" class="btn btn-primary">发布公告</button>
                            </p>
                            <s:fielderror fieldName="notice_result"></s:fielderror>

                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"/>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"/>
<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"/>
<script id="editor" type="text/plain" name="gdesc" style="width:100%;height:350px;"/>
<script type="text/javascript">
    //实例化编辑器
    var ue = UE.getEditor('ueditor', {});

</script>

<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#signupForm").validate();
    });
</script>

</body>
</html>
