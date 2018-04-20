<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2018/4/14
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看选课结果（学生）</title>
</head>
<body>
<h1>查看选课结果</h1>
    <c:url var="resultsControllerUrl" value="/resultsController"/>
    <table border="1">
        <thead>
            <td>序号</td>
            <td>课程类型</td>
            <td>课程编号</td>
            <td>课程名称</td>
            <td>学时</td>
            <td>学分</td>
            <td>教师</td>
            <td>上课时间</td>
            <td>状态</td>
        </thead>
        <c:forEach var="courseSelections" items="${courseSelections}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${courseSelections.course.courseType.no}</td>
                <td>${courseSelections.course.no}</td>
                <td>${courseSelections.course.title}</td>
                <td>${courseSelections.course.hours}</td>
                <td>${courseSelections.course.credit}</td>
                <td>${courseSelections.course.teacher.name}</td>
                <td>${courseSelections.course.time}</td>
                <td>${courseSelections.course.status}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
