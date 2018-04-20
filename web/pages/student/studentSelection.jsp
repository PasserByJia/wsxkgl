<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2018/4/14
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选课</title>
</head>
<body>
<h1>选课</h1>
<c:url var="find" value="/selectionController" />
<form action="${find}" method="post">
    查询条件：
    <select name="courseTypeId">
        <option value="0">请选择</option>
        <c:forEach var="courseType" items="${courseTypes}">
            <option value="${courseType.id}">
                ${courseType.description}
            </option>
        </c:forEach>
    </select>
    <input type="text" name="title"/>
    <input type="submit" value="查询">
</form>
<table border="1">
    <thead>
    <td>序号</td>
    <td>课程类型</td>
    <td>课程编号</td>
    <td>课程名称</td>
    <td>学时</td>
    <td>学分</td>
    <td>教师</td>
    <td>职称</td>
    <td>性别</td>
    <td>已选人数</td>
    <td>人数上限</td>
    <td>上课时间</td>
    <td>选课</td>
    </thead>

    <c:forEach var="course" items="${courses}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${course.courseType.description}</td>
            <td>${course.no}</td>
            <td>${course.title}</td>
            <td>${course.hours}</td>
            <td>${course.credit}</td>
            <td>${course.teacher.name}</td>
            <td>${course.teacher.proTitle.description}</td>
            <td>${course.teacher.sex}</td>
            <td>${course.accumulation}</td>
            <td>${course.max}</td>
            <td>${course.time}</td>
            <td>
                <c:set var="flag" value="true" />
                <c:forEach var="courseSelections" items="${courseSelections}" varStatus="status">
                        <c:if test="${courseSelections.student.id ==student.id && courseSelections.course.id==course.id}">
                            已选
                            <c:set var="flag" value="false"/>
                        </c:if>
                </c:forEach>
                <c:if test="${flag }">
                    <c:url var="selectUrl" value="/selectionControllerAdd?courseId=${course.id}"/>
                    <a href="${selectUrl}">选课</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
