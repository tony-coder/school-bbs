<%@page import="cn.edu.zjut.po.User"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.edu.zjut.serviceImpl.AdminServiceImpl" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    if (session.getAttribute("username") == null){
        response.sendRedirect(path+"/manage/admin.jsp");
        return ;
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'change-admin.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/titlebar.css" rel="stylesheet">
    <!--
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
      <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
       -->
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</head>

<body>

<%--<jsp:include page="/pages/header.jsp"/>--%>

<div class="container" style="margin-top: 80px">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation" ><a href="<%=path%>/manage/notice.jsp">发布公告</a></li>
                <li role="presentation" class="active"><a href="<%=path%>/manage/change-admin.jsp">资料修改</a></li>
                <li role="presentation"><a href="<%=path%>/manage/newpost.jsp">查看新帖</a></li>
                <li role="presentation"><a href="<%=path%>/manage/bestpost.jsp">精华帖请求</a></li>
                <li role="presentation"><a href="<%=path%>/manage/limit.jsp">封锁用户</a></li>
                <li role="presentation"><a href="<%=path%>/manage/create_discuss.jsp">创建讨论区</a></li>
            </ul>
        </div>

        <div class="col-md-9">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        个人信息修改
                    </h3>
                </div>
                <div class="panel-body">
                    <form id="form1" action="<%=path%>/adminupdate.action" method="post" enctype="multipart/form-data">

                        <div class="column">
                            <%
                                ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
                                AdminServiceImpl adminService = (AdminServiceImpl)context.getBean("adminService");
                                User admin = adminService.getAdminById((Integer)session.getAttribute("userId"));
                            %>

                            <div class="form-group">
                                <img alt="@zhangjianhao" class="avatar left" height="70" src="<%=path+"/"+admin.getUserAvatarUrl() %>" width="70" />
                                请上传你的头像<br/>
                            </div>
                            <input type="file" id="inputfile" accept="image/*" name="photoImg"><br/>

                            <div class="form-group">
                                <label for="name">用户名</label>
                                <input id="username" type="text" class="form-control" id="name" width="200px" height="80px" placeholder="请输入名称" name="username" value="<%=admin.getUsername()%>"><s:fielderror fieldName="username"></s:fielderror>
                            </div>

                            <div class="form-group">
                                <label for="name">性 别</label><br/>
                                <%if (admin.getSex().equals("男")){%>
                                    <input type="radio" name="sex" value="男" checked="checked"/>男
                                    &nbsp &nbsp<input type="radio" name="sex" value="女">女
                                <%} else if (admin.getSex().equals("女")){%>
                                    <input type="radio" name="sex" value="男" />男
                                    &nbsp &nbsp<input type="radio" name="sex" value="女" checked="checked"/>女
                                <%} else {%>
                                    <input type="radio" name="sex" value="男" />男
                                    &nbsp &nbsp<input type="radio" name="sex" value="女"/>女
                                <%} %>
                            </div>


                            <dl class="form-group">
                                <dt><label for="user_profile_blog">邮箱</label></dt>
                                <dd><input id="email" type="email" class="form-control" id="user_profile_blog" name="email" size="30" value="<%=admin.getEmail()%>" /></dd>
                                <s:fielderror fieldName="email"></s:fielderror>
                            </dl>
                            <dl class="form-group">
                                <dt><label for="user_profile_company">密码</label></dt>
                                <dd><input id="password" class="form-control" name="password" size="30" type="password" width="200px" /></dd>
                            </dl>
                            <dl class="form-group">
                                <dt><label for="user_profile_location">重复密码</label></dt>
                                <dd><input id="confirm_password" class="form-control" name="confirm_password" size="30" type="password" width="200px" /></dd>
                            </dl>
                            <p><input type="submit" value="确认修改"></p>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(function(){
        $("#form1").validate({
            rules:{
                username:{
                    required:true
                },
                email:{
                    required:true,
                    email:true
                },
                password:{
                    rangelength:[6,20]
                },
                confirm_password:{
                    equalTo:"#password"
                }
            },
            messages:{
                username:{
                    required:"必填"
                },
                email:{
                    required:"必填",
                    email:"E-Mail格式不正确"
                },
                password:{
                    rangelength: $.validator.format("密码最小长度:{0}, 最大长度:{1}。")
                },
                confirm_password:{
                    equalTo:"两次密码输入不一致"
                }
            }
        });
    });
</script>
<style type="text/css">
    .error{
        color:red;
    }
</style>
</html>
