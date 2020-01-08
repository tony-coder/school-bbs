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

    <title>发布公告</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>

<jsp:include page="/page/header.jsp"/>

<div class="container" style="margin-top: 80px">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"  class="active"><a href="<%=path%>/manager/notice.jsp">发布公告</a></li>
                <li role="presentation"><a href="<%=path%>/manager/change_info.jsp">资料修改</a></li>
                <li role="presentation"><a href="more.action?type=-1&&page=1&&isAdmin=1">查看新帖</a></li>
                <li role="presentation"><a href="bestTopic.action">精华帖请求</a></li>
                <li role="presentation"><a href="getAllLimitUsers.action">封锁用户</a></li>
                <li role="presentation"><a href="<%=path%>/manager/create_discuss.jsp">创建讨论区</a></li>
                <li role="presentation"><a href="getAllSensitiveWords">敏感词管理</a></li>
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
                        <form action="postNotice.action" method="post" onsubmit="return onUpdateContent();">
                            <input type="hidden" name="notice.content" id="content">
                            <div class="form-group">
                                <label for="name">公告标题</label>
                                <input required type="text" class="form-control" name="notice.title" id="name"
                                       width="200px"
                                       height="80px" placeholder="请输入标题">
                            </div>
                            <dl class="form-group">
                                <dt><label>公告内容</label></dt>
                                <dd>
                                    <script id="ueditor" type="text/plain" style="height: 20px"></script>
                                </dd>
                            </dl>
                            <p>
                                <button type="submit" class="btn btn-primary">发布公告</button>
                            </p>
                            <s:fielderror fieldName="notice_result"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor/lang/zh-cn/zh-cn.js"></script>
<script id="editor" type="text/plain" name="gdesc" style="width:100%;height:350px;"></script>
<script type="text/javascript">
    //实例化编辑器
    var ue = UE.getEditor('ueditor', {
        toolbars: [
            [
                'undo', //撤销
                'bold', //加粗
                'underline', //下划线
                'preview', //预览
                'horizontal', //分隔线
                'inserttitle', //插入标题
                'cleardoc', //清空文档
                'fontfamily', //字体
                'fontsize', //字号
                'paragraph', //段落格式
                'simpleupload', //单图上传
                'insertimage', //多图上传
                'attachment', //附件
                'inserttable', //插入表格
                'emotion', //表情
                'justifyleft', //居左对齐
                'justifyright', //居右对齐
                'justifycenter', //居中对
                'justifyjustify', //两端对齐
                'forecolor', //字体颜色
                'fullscreen', //全屏
                'edittip ', //编辑提示
                'customstyle', //自定义标题
            ]
        ]
    });

    /*获取输入区的内容*/
    function onUpdateContent() {
        var content = document.getElementById("content");
        content.value = UE.getEditor("ueditor").getContent();
        // alert(content.value);
        return true;
    }

</script>

<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#signupForm").validate();
    });
</script>

</body>
</html>
