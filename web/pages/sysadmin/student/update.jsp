<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/14
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>更新学生信息</h1>
<c:url var="updateStudentUrl" value="/studentUpdateController"/>
<form action="${updateStudentUrl}" method="post">
    <input type="text" name="id" value="${studentToUpdate.id}" hidden/>
    姓名
    <input type="text" name="name" value="${studentToUpdate.name}"/>
    <br/>
    学号
    <input type="text" name="no" value="${studentToUpdate.no}"/>
    <br/>
    性别
    <input type="radio" name="sex" value="男" checked>男
    <input type="radio" name="sex" value="女">女
    <br/>
    <input type="submit"/>
</form>
</body>
</html>
