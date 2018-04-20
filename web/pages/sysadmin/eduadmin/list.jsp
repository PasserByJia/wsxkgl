<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/14
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>教务管理员管理</h1>
<c:url var="addEduAdminUrl" value="/pages/sysadmin/eduadmin/add.jsp"/>
<a href="${addEduAdminUrl}">新建</a>
<c:url var="find" value="eduAdminController" />
<form action="${find}" method="post">
     <input type="text" name="string" placeholder="输入工号/学号/姓名查询 " />
    <input type="submit" value="查询">
</form>
<table border="1">
    <thead>
    <td>序号</td>
    <td>工号</td>
    <td>姓名</td>
    <td colspan="3">操作</td>
    </thead>
    <c:forEach var="eduAdmin" items="${eduAdmins}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td> ${eduAdmin.no}</td>
            <td>${eduAdmin.name}</td>
            <td>
                <c:url var="updateUrl"
                       value="/eduAdminUpdateController?id=${eduAdmin.id}"/>
                <a href="${updateUrl}">修改</a>
            </td>
            <td>
                <c:url var="deleteUrl"
                       value="/eduAdminController?action=delete&id=${eduAdmin.id}"/>
                <a href="${deleteUrl}">删除</a>
            </td>
            <td>
                <c:url var="deleteUrl"
                       value="/eduAdminUpdateController?action=reset&id=${eduAdmin.id}"/>
                <a href="${deleteUrl}">重置密码</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
