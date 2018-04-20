<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/4
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Menu</title>
    </head>
    <body>
    ${sysadmin.name}管理员<br/>
        <c:url var="teacherList" value="/teacherController"/>
        <a href="${teacherList}" target="main">教师用户管理</a>
        <br/>
        <c:url var="studentList" value="/studentController"/>
        <a href="${studentList}" target="main">学生用户管理</a>
        <br/>
        <c:url var="eduAdminList" value="/eduAdminController"/>
        <a href="${eduAdminList}" target="main">教务员用户管理</a>
        <br/>
        <c:url var="notice" value="/noticeController"/>
        <a href="${notice}" target="main">查看通知</a>
        <br/>
        <c:url value="/pages/updatePassword.jsp" var="updatePassword"/>
        <a href="${updatePassword}"  target="main" >修改密码</a>
        <br/>
        <c:url var="backup" value="/pages/sysadmin/backup.jsp"/>
        <a href="${backup}" target="main">备份和恢复</a>
        <br/>
        <c:url var="init" value="/pages/sysadmin/init.jsp"/>
        <a href="${init}" target="main">系统初始化</a>
        <br>
        <c:url var="logoutUrl" value="/loginController" />
        <a href="${logoutUrl}" target="_top">安全退出</a>
        <br/>
    </body>
</html>
