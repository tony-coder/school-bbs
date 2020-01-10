<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>搜索结果</title>


</head>


  <body>
  <jsp:include page="/page/header.jsp"/>

   <div class="list-group search-list">
    <a href="#" class="list-group-item active">
        <s:if test="%{#request.topics!=null}">
            搜索结果如下：
        </s:if>
        <s:else>
            抱歉！未找到符合的结果
        </s:else>
    </a>
       <s:iterator value="%{#request.topics}" id="topic">
           <a href="<%=path%>/topicDetail.action?topicId=<s:property value="%{#topic.id}"/>&&pageNum=1" class="list-group-item">
               <h4 class="list-group-item-heading">
                   [<s:property value="%{#topic.subSectionBySectionId.mainSectionByMainSectionId.title}"/>]

               </h4>
               <s:property value="%{#topic.title}"/>&nbsp[<s:property value="%{#topic.subSectionBySectionId.title}"/>]
                   <p class="text-right post-date">浏览量:<s:property value="%{#topic.click}"/>&nbsp;评论量:<s:property value="%{#topic.replyNum}"/>&nbsp;发表日期:<s:property value="%{#topic.createTime}"/></p>
           </a>
       </s:iterator>
    </div>
  </body>
</html>
