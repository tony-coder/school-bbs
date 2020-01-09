<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/23
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";  //获取web应用根目录路径
%>
<html>
<head>
    <title>帖子浏览</title>
    <%--<link href="<%=basePath%>css/post-detail.css" rel="stylesheet">--%>
    <%--<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">--%>
    <%-- <script type="text/javascript" src="ckeditor/ckeditor.js"></script>--%>
</head>
<body>
<div style="height:100%">

     <jsp:include page="/page/header.jsp"/>
    <div class="container" style="margin-top: 50px">
        <div class="row">
            <div class="col-md-1 post-border"></div>
            <div class="col-md-2 post-head">
                <!--<img src="img/tm-bg-slide-1.jpg" width="80px" height="80px" class="img-responsive img-circle">-->
                <img alt="" class="img-responsive img-circle"
                     src="<%=basePath%><s:property value="#request.topic.getUserByUserId().getUserAvatarUrl()"/>"
                     style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>
                <span class="user-info">
                            <span class="badge" style="background: #f1c40f;margin-top: 5px">发帖者</span>
                            :<span class="badge"
                                   style="background: #f1c40f;margin-top: 5px"><s:property
                        value="#request.topic.getUserByUserId().getUsername()"/> </span>
                        </span><br/>
                <span class="user-info">
                            <span class="badge" style="background: #2ecc71;margin-top: 5px">性别</span>
                            :<span class="badge"
                                   style="background: #2ecc71;margin-top: 5px"><s:property
                        value="#request.topic.getUserByUserId().getSex()"/></span>
                        </span><br/>
                <span class="user-info">
                             <span class="badge" style="background: #ff6927;margin-top: 5px">等级</span>:
                            <span class="badge" style="background: #ff6927;margin-top: 5px">LV<s:property
                                    value="#request.topic.getUserByUserId().getLevel()"/></span>
                </span><br>
            </div>
            <div class="col-md-8 post-content">
                <div class="post-title">
                    <h2 style="margin-left:20px;color:black">[<s:property
                            value="#request.topic.subSectionBySectionId.title"/>]<s:property
                            value="#request.topic.title"/>
                    </h2>
                    <div style="margin-left:20px">
                        <span class="glyphicon glyphicon-comment"></span>&nbsp;&nbsp;<s:property
                            value="#request.topic.replyNum"/> &nbsp;|&nbsp;<span>发表于:<s:date
                            name="#request.topic.createTime" format="yyyy-MM-dd HH:mm:ss"/></span>
                        <s:if test="#request.topic.userByUserId.username == #session.user.username">
                            <a style="float:right;margin-right: 20px;"
                               href="<%=path%>/editTopic.action?topic.id=<s:property value="#request.topic.id"/>">编辑</a>
                        </s:if>
                    </div>
                    <strong style=" float:right;margin-right:10px;margin-top: 10px">
                        <span class="badge" style="background: #ff6927;width: 50px;">楼主</span></strong>
                </div>
                <div style="margin: 20px">
                    <s:property escape="false" value="#request.topic.content"/>
                </div>
            </div>
            <div class="col-md-1 post-border">
            </div>
        </div>
    </div>

    <!-- 回复内容 -->
    <s:set name="floor" value="(#request.pageNum - 1)*5"/>
    <s:iterator value="#request.replies">
        <s:set name="floor" value="#floor+1"/>
        <div class="container">
            <div class="row" style="margin-top: 5px">
                <div class="col-md-1 reply-border">
                </div>
                <div class="col-md-2 reply-head">
                    <img alt="" class="img-responsive img-circle"
                         src="<%=path%><s:property value="userByUserId.userAvatarUrl"/>"
                         style="margin:1px 1px;width: 120px;height: 120px;margin: 30px auto;"/>

                    <span class="user-info">
                        <span class="badge" style="background: #f1c40f;margin-top: 5px">姓名</span>
                        :<span class="badge" style="background: #f1c40f;margin-top: 5px"><s:property
                            value="userByUserId.username"/></span>
                    </span><br/>
                    <span class="user-info">
                        <span class="badge" style="background: #2ecc71;margin-top: 5px">性别</span>
                        :<span class="badge" style="background: #2ecc71;margin-top: 5px"><s:property
                            value="userByUserId.sex"/></span>
                    </span><br/>
                    <span class="user-info">
                         <span class="badge" style="background: #ff6927;margin-top: 5px">等级</span>:
                        <span class="badge" style="background: #ff6927;margin-top: 5px">LV<s:property
                                value="userByUserId.level"/></span>
                    </span>
                    <br>
                </div>
                <div class="col-md-8 reply-content">

                    <div class="reply-time">
                        <span style="color: gray">回复于:<s:date name="replyTime" format="yyyy-MM-dd HH:mm:ss"/></span>
                        <s:if test="#floor == 1">
                             <span class="badge"
                                   style="float:right;margin-right:10px;background: #ff6927;width: 50px;">沙发</span>
                        </s:if>
                        <s:elseif test="#floor == 2">
                             <span class="badge"
                                   style="float:right;margin-right:10px;background: #ff5959;width: 50px;">板凳</span>
                        </s:elseif>
                        <s:elseif test="#floor == 3">
                            <span class="badge"
                                  style="float:right;margin-right:10px;background: #4b9ded;width: 50px;">地板</span>
                        </s:elseif>
                        <s:elseif test="">
                            <span class="badge"
                                  style="float:right;margin-right:10px;background: #4b9ded;width: 50px;">第<s:property
                                    value="#floor"/> </span>
                        </s:elseif>
                    </div>
                    <div style="margin: 20px;">
                        <s:property escape="false" value="content"/>
                    </div>
                </div>
                <div class="col-md-1 reply-border">
                </div>
            </div>
        </div>
    </s:iterator>
    <ul class="pagination pagination-lg" style="float:right">
        <s:if test="#request.pageNum > 1">
            <s:set name="pageIndex" value="#request.pageNum - 1"/>
            <li>
                <a href="topicDetail.action?topicId=<s:property value="#request.topic.id"/>&&pageNum=<s:property value="#pageIndex"/>">
                    &laquo;</a></li>
        </s:if>
        <s:if test="#request.pageNum <=5">
            <s:bean name="org.apache.struts2.util.Counter" id="counter">
                <s:param name="first" value="0"/>
                <s:param name="last" value="4"/>
                <s:iterator>
                    <s:if test="#request.pageNum == #counter.current">
                        <li class="active"><a
                                href="topicDetail.action?topicId=<s:property value="#request.topic.id"/>&&pageNum=<s:property value="#counter.current"/>"><s:property
                                value="#counter.current"/>
                        </a></li>
                    </s:if>
                    <s:else>
                        <li>
                            <a href="topicDetail.action?topicId=<s:property value="#request.topic.id"/>&&pageNum=<s:property value="#counter.current"/>"><s:property
                                    value="#counter.current"/>
                            </a></li>
                    </s:else>
                    <s:if test="#counter.current == 5">
                        <li>
                            <a href="topicDetail.action?topicId=<s:property value="#request.topic.id"/>&&pageNum=6">&raquo;</a>
                        </li>
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
                                href="topicDetail.action?topicId=<s:property value="#request.topic.id"/>&&pageNum=<s:property value="#pageIndex"/>"><s:property
                                value="#pageIndex"/>
                        </a></li>
                    </s:if>
                    <s:else>
                        <li class=""><a
                                href="topicDetail.action?topicId=<s:property value="#request.topic.id"/>&&pageNum=<s:property value="#pageIndex"/>"><s:property
                                value="#pageIndex"/>
                        </a></li>
                    </s:else>
                </s:iterator>
            </s:bean>
            <li>
                <a href="topicDetail.action?topicId=<s:property value="#request.topic.id"/>&&pageNum=<s:property value="#maxPage"/>">&raquo;</a>
            </li>
        </s:if>
    </ul>
    <br>

    <div style="height: 200px;margin: 70px auto; width: 800px;">
        <s:fielderror fieldName="limit"/>
        <form action="reply.action" method="post" onsubmit="return onUpdateContent();">
            <input type="hidden" name="reply.content" id="content">
            <input type="hidden" name="reply.topicByTopicId.id" value=<s:property value="#request.topic.id"/>>
            <input type="hidden" name="pageNum" value=
            <s:property value="#request.pageNum"/>>
            <div style="margin: 5px auto;height: 100px; width: 800px">
                <%--<textarea id="TextArea1" cols="20" rows="1" name="content" class="ckeditor"></textarea>--%>
                <script id="ueditor" type="text/plain" style="height: 20px"></script>
            </div>
            <div style="float:right;margin: 60px auto">
                <input type="submit" class="btn btn-primary" style="width: 60px;" value="回复"/>
            </div>
        </form>
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

</body>
</html>
