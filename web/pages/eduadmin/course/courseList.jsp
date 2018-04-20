<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/4/14
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List</title>
</head>
<body>
<h1>课程管理</h1>
    <c:url var="courseControllerAdd" value="/courseControllerAdd"/>
    <a href="${courseControllerAdd}">新建</a>
    <c:url var="find" value="/courseController" />
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
            <td>上课时间</td>
            <td colspan="2">操作</td>
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
                <td>${course.min}</td>
                <td>${course.max}</td>
                <td>${course.time}</td>
                <td>
                    <c:url var="updateUrl"
                           value="/courseControllerUpdate?id=${course.id}"/>
                    <a href="${updateUrl}">修改</a>
                </td>
                <td>
                    <c:url var="deleteUrl"
                           value="/courseControllerDelate?id=${course.id}"/>
                    <a href="${deleteUrl}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
