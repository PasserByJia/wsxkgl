<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/4/14
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<h1>添加课程</h1>
<c:url var="addUrl" value="/courseControllerAdd"/>
<form action="${addUrl}" method="post">
    <table>
        <thead>
        <td>课程类型</td>
        <td>课程编号</td>
        <td>课程名称</td>
        <td>学时</td>
        <td>学分</td>
        <td>教师</td>
        <td>人数下限</td>
        <td>人数上限</td>
        <td>上课时间</td>
        </thead>
        <tr>
            <td>
                <select name="courseType">
                    <c:forEach var="courseType" items="${courseTypeSet}">
                        <option value="${courseType.id}">
                                ${courseType.description}
                        </option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="text" name="courseNo" /></td>
            <td><input type="text" name="courseTitle"/></td>
            <td><input type="text" name="courseHours"/></td>
            <td><input type="text" name="courseCredit" /></td>

            <td>
                <select name="courseTeacher">
                    <c:forEach var="teacher" items="${teacherSet}">
                        <option value="${teacher.id}">
                                ${teacher.name}
                        </option>
                    </c:forEach>
                </select>
            </td>

            <td><input type="text" name="courseMin" /></td>
            <td><input type="text" name="courseMax" /></td>
            <td><input type="text" name="courseTime" /></td>
        <tr/>
        <div align="center">
            <input type="submit" value="确定" />
        </div>
    </table>
</form>
</body>
</html>
