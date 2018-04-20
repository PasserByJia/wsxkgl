<%--
  Created by IntelliJ IDEA.
  User: liuyang
  Date: 2018/4/14
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看选课结果</title>
</head>
<body>
<h1>查看选课结果</h1>
<table border="1">
    <thead>
    <td>序号</td>
    <td>课程类型</td>
    <td>课程编号</td>
    <td>课程名称</td>
    <td>学时</td>
    <td>学分</td>
    <td>上课时间</td>
    <td>人数</td>
    <td>操作</td>
    </thead>
    <c:forEach var="course" items="${coursesToReturn}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${course.courseType.description}</td>
                <td>${course.no}</td>
                <td>${course.title}</td>
                <td>${course.hours}</td>
                <td>${course.credit}</td>
                <td>${course.time}</td>
                <td>${course.accumulation}</td>
                <td>
                    <%--c:url var="deleteUrl" value="/teacherController?action=delete&id=${teacher.id}"/>--%>
                    <a href="${deleteUrl}">导出学生名单</a>
                </td>
            </tr>
    </c:forEach>
</table>
</body>
</html>