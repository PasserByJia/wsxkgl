<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 晓
  Date: 2018/4/14
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>选课结果管理</h1>
<c:url var="find" value="/selectionResultController" />
<form action="${find}" method="post">
    <input type="text" name="title"/>
    <input type="submit" value="查询">
</form>
<br>
<table border="1">
    <thead>
        <td>序号</td>
        <td>课程类型</td>
        <td>课程编号</td>
        <td>课程名称</td>
        <td>学时</td>
        <td>学分</td>
        <td>教师</td>
        <td>人数下限</td>
        <td>人数上限</td>
        <td>实选人数</td>
        <td>默认状态</td>
        <td>操作</td>
    </thead>
    <c:forEach var="course" items="${courses}" varStatus="status">
        <tr>
            <td> ${status.index+1}</td>
            <td> ${course.courseType.description}</td>
            <td> ${course.no}</td>
            <td> ${course.title}</td>
            <td>${course.hours}</td>
            <td>${course.credit}</td>
            <td>${course.teacher.name}</td>
            <td>${course.min}</td>
            <td>${course.max}</td>
            <td>${course.accumulation}</td>
            <td>${course.status}</td>
            <td>
                <c:url var="editSelectionStatus" value="/editSelectionStatusController?id=${course.id}"></c:url>
                <a href="${editSelectionStatus}">
                    <c:if test="${true==course.status}">
                        失败
                    </c:if>
                    <c:if test="${false==course.status}">
                        成功
                    </c:if>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
    <a href="/pages/eduadmin/selection/ExitCourse.jsp">处理课程退选申请</a> &nbsp;
    <c:url var="find" value="/findCourseController" />
    <a href="${find}">处理课程补选申请</a>
</body>
</html>
