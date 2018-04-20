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
<h1>学生管理</h1>
<c:url var="addStudentUrl" value="/pages/sysadmin/student/add.jsp"/>
<a href="${addStudentUrl}">新建</a>
<c:url var="find" value="studentController" />
<form action="${find}" method="post">
   <input type="text" name="string" placeholder="输入工号/学号/姓名查询 "/>
    <input type="submit" value="查询">
</form>
<table border="1">
    <thead>
    <td>序号</td>
    <td>学号</td>
    <td>姓名</td>
    <td colspan="3">操作</td>
    </thead>
    <c:forEach var="student" items="${students}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${student.no}</td>
            <td>${student.name}</td>
            <td>
                <c:url var="updateUrl"
                       value="/studentUpdateController?id=${student.id}"/>
                <a href="${updateUrl}">修改</a>
            </td>
            <td>
                <c:url var="deleteUrl"
                       value="/studentController?action=delete&id=${student.id}"/>
                <a href="${deleteUrl}">删除</a>
            </td>
            <td>
                <c:url var="restUrl"
                       value="/studentUpdateController?action=reset&id=${student.id}"/>
                <a href="${restUrl}">重置密码</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
