<%@ page import="cn.edu.zjut.po.Topic" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="cn.edu.zjut.po.MainSection" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.zjut.service.MainSectionService" %>
<%@ page import="cn.edu.zjut.po.SubSection" %>
<%@ page import="java.util.Set" %>
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

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    MainSectionService mainSectionService = (MainSectionService) ctx.getBean("mainSectionService");
    List<MainSection> mainSections = mainSectionService.getAllMainSection();  //获取所有主板库
    //for (MainSection mainSection : mainSections) {
%>
<html>
<head>
    <title>Title</title>
    <link type="text/css" href="css/publish-post.css" rel="stylesheet">
</head>
<body>
<%--<jsp:include page="/pages/header.jsp"/>--%>
<table class="tb" cellspacing="0" cellpadding="3">
    <%Topic topic = (Topic) request.getAttribute("topic");%>
    <% if (topic == null) { %>
    <form method="post" action="<%=path%>/publish.action"><% }else{ %> <%--新建帖子--%>
        <form method="post" action="<%=path%>/updatepost.action?postId=<%=topic.getId()%>">
            <% }%>  <%--更新帖子--%>
            <tr>
                <th>文章标题</th>
                <td>
                    <%--<s:if test="%{#request.topic == null}">
                        <input required type="text" id="textfile" name="title"/> &lt;%&ndash;新建帖子&ndash;%&gt;
                    </s:if>
                    <s:else>
                        <input required type="text" id="textfile" name="title" value="<%=topic.getTitle()%>"/> &lt;%&ndash;更新帖子&ndash;%&gt;
                    </s:else>--%>
                    <% if (topic == null) { %>
                    <input required type="text" id="textfile" name="title"/> <%--新建帖子--%>
                    <% } else { %>
                    <input required type="text" id="textfile" name="title" value="<%=topic.getTitle()%>"/> <%--更新帖子--%>
                    <% }%>
                    <s:fielderror fieldName="limit"/>
                    <span>你最多可以输入30个字符</span>
                </td>
            </tr>
            <tr>
                <th>文章内容</th>
                <td>
                    <% if (topic == null) {%>
                    <%--<textarea type="text" class="" name="content" id="ueditor"></textarea>--%>
                    <script id="ueditor" name="content" type="text/plain" style="height: 300px"></script>
                    <%--新建帖子--%>
                    <% } else { %>
                    <script id="ueditor" name="content" type="text/plain" style="height: 300px"><%=topic.getContent()%>
                    </script>
                    <%--更新帖子--%>
                    <% }%>
                </td>
            </tr>
            <tr>
                <th>选择版块</th>
                <td>
                    <div id="change" style="float:left">
                        <!-- <a class="btn-select" id="big_btn_select"> -->
                        <select id="mainSection" name="mainSection" onchange="onselected(this)">
                            <%for (MainSection mainSection : mainSections) {%>
                            <option value=<%=mainSection.getId()%>><%=mainSection.getTitle() %>
                            </option>
                            <%}%>
                        </select>
                        <%
                            int i = 0;
                            for (MainSection mainSection : mainSections) {  //不做处理的话会使得所有的下拉框都显示出来
                                if (i == 0) {
                        %>
                        <select name="subSection" id="<%=mainSection.getId()%>">
                                <%}else{%>
                            <select name="sub" id="<%=mainSection.getId()%>" style="display: none;">
                                <%
                                    }
                                    Set<SubSection> subSections = (Set<SubSection>) mainSection.getSubSectionsById();
                                    for (SubSection subSection : subSections) {
                                %>
                                <option value="<%=subSection.getId()%>"><%=subSection.getTitle()%>
                                </option>
                                <%}%>
                            </select>
                                <%i++;}%>
                    </div>

                    <span style="float:right;line-height:35px;">请选择所要发帖的版块</span>
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

<script type="text/javascript">
    /*实现动态更新子版块*/
    function onselected(obj) {

        var mainSection = document.getElementById("mainSection");
        //console.log("size:" + mainSection.length);
        for (var i = 0; i < mainSection.length; i++) {
            //console.log("main:" + mainForum[i].value);
            var sub = document.getElementById(mainSection[i].value);
            sub.style.display = "none";
            sub.name = "sub"
            //console.log("sub value:" + sub.value);
        }
        var value = obj.value;
        //console.log("select value:" + value);
        var subSection = document.getElementById(value);
        subSection.style.display = "";
        subSection.name = "subSection"
    }
</script>
</body>
</html>
