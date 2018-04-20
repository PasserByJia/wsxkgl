<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/14 0014
  Time: 上午 6:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<div>
    <head>
        <title>用户登录</title>
    </head>
    <body>
    <%--构造loginUrl地址--%>
    <c:url var="loginUrl" value="/loginController"/>
    <div align="center" style="margin-top: 100px">
        <h2>请登录惟实学院选课管理系统</h2><br>
        ${msg}
        <form action="${loginUrl}" method="post">
            角色
            <select name="role">
                <option value="1">任课教师</option>
                <option value="2">学生</option>
                <option value="3">系统管理员</option>
                <option value="4">教务管理员</option>
            </select>
            <br>
            用户名
            <%--文本框中输入用户名--%>
            <input type="text" name="username"/>
            <br/>
            密&nbsp&nbsp码
            <%--文本框中输入密码--%>
            <input type="password" name="password">
            <br/>
            <%--提交按钮--%>
            <input type="submit">
        </form>
    </body>
</div>
</html>
