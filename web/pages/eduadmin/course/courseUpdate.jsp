<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/4/14
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>课程修改</h1>
<c:url var="courseUpdateUrl" value="/courseControllerUpdate"/>
<form action="${courseUpdateUrl}" method="post">
    <table border="1" width="50">
        <thead>
            <td>课程编号</td>
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
            <td><input type="text" name="id" value="${courseToUpdate.id}" hidden/></td>
            <td>
                <select name="courseType" >
                    <c:forEach var="courseType" items="${courseTypeSet}">
                        <option value="${courseType.id}"
                                <c:if test="${courseType.id==courseToUpdate.courseType.id}">
                                selected=selected
                                </c:if>
                        >
                                ${courseType.description}
                        </option>
                    </c:forEach>
                </select>
            </td>

            <td><input type="text" name="courseNo" value="${courseToUpdate.no}"/></td>

            <td><input type="text" name="courseTitle" value="${courseToUpdate.title}"/></td>
            <td><input type="text" name="courseHours" value="${courseToUpdate.hours}"/></td>
            <td><input type="text" name="courseCredit" value="${courseToUpdate.credit}"/></td>

            <td>
                <select name="courseTeacher">
                    <c:forEach var="teacher" items="${teacherSet}">
                        <option value="${teacher.id}"
                                <c:if test="${teacher.id==courseToUpdate.teacher.id}">
                                    selected=selected
                                </c:if>
                        >
                                ${teacher.name}
                        </option>
                    </c:forEach>
                </select>
            </td>

            <td><input type="text" name="courseMin" value="${courseToUpdate.min}"/></td>
            <td><input type="text" name="courseMax" value="${courseToUpdate.max}"/></td>
            <td><input type="text" name="courseTime" value="${courseToUpdate.time}"/></td>
        </tr>
    </table>
    <input type="submit" value="确定" />
</form>
</body>
</html>
