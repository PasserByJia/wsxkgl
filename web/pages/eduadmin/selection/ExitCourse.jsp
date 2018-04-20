<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/15 0015
  Time: 下午 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>课程退选</h1>
<c:url var="find" value="/exitCourseController" />
<form action="${find}" method="post">
    学号
    <input type="text" name="studentid"/>
    <input type="submit">
</form>

<br>
<table border="1">
    <thead>
    <td>序号</td>
    <td>课程编号</td>
    <td>课程名称</td>
    <td>学号</td>
    <td>姓名</td>
    <td>状态</td>
    <td>操作</td>
    </thead>
    <c:forEach var="selectionResult" items="${courseSelections}" varStatus="status">
        <tr>
            <td> ${status.index+1}</td>
            <td> ${selectionResult.course.no}</td>
            <td> ${selectionResult.course.title}</td>
            <td>${selectionResult.student.username}</td>
            <td>${selectionResult.student.name}</td>
            <td>${selectionResult.course.status}</td>
            <td>
                <c:url var="editSelectionStatus" value="/exitCourseAcodingStuUsernameController?courseSelectionId=${selectionResult.id}"></c:url>
                <a href="${editSelectionStatus}">退选</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
