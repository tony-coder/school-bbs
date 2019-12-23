<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/23
  Time: 20:58
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
    <link type="text/css" href="css/publish-post.css" rel="stylesheet">
</head>
<body>
<%--<jsp:include page="/pages/header.jsp"/>--%>
<table class="tb" cellspacing="0" cellpadding="3">

    <form method="post" action="#">
        <tr>
            <th>文章标题</th>
            <td>
                <input required type="text" id="textfile" name="title" value="title"/>
                <s:fielderror fieldName="limit"/>
                <span>你最多可以输入30个字符</span>
            </td>
        </tr>
        <tr>
            <th>文章内容</th>
            <td>
                <%--<textarea type="text" class="" name="content" id="ueditor"></textarea>--%>
                <script id="ueditor" name="content" type="text/plain" style="height: 500px">test</script>
            </td>
        </tr>
        <tr>
            <th></th>
            <td>
                <input type="submit" value="提交"/>
            </td>
        </tr>
    </form>
</table>

<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
<script id="editor" type="text/plain" name="gdesc" style="width:100%;height:350px;"></script>
<script type="text/javascript">
    //实例化编辑器
    var ue = UE.getEditor('ueditor', {});
</script>

</body>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#signupForm").validate();
    });
</script>
</body>
</html>
