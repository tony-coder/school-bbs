
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.edu.zjut.service.AdminService" %>
<%@ page import="cn.edu.zjut.po.User" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%--仅测试！！！--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    if (session.getAttribute("user") == null){
        response.sendRedirect(path+"/manage/admin.jsp");
        return ;
    }
    User admin = (User)session.getAttribute("user");
    System.out.println(admin.getUsername());
%>
